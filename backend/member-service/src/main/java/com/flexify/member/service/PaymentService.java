package com.flexify.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.*;
import com.flexify.member.entities.*;
import com.flexify.member.repository.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Random;

@Service
public class PaymentService {
	@Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    private MemberMembershipRepository membershipRepo;

    @Autowired
    private PlanRepository planRepo;

    /* ================= TRANSACTION ID ================= */
    private String generateTransactionId() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(10);
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            sb.append(chars.charAt(random.nextInt(chars.length())));
        }
        return sb.toString();
    }

    /* ================= PAYMENT ================= */
    public PaymentResponseDTO makePayment(PaymentRequestDTO dto) {

        /* SAVE PAYMENT */
        Payment payment = new Payment();
        payment.setMid(dto.getMemberId());
        payment.setAmount(dto.getAmount());
        payment.setPaymentDate(dto.getPaymentDate());
        payment.setPaymentMethod(dto.getPaymentMethod());
        payment.setTransactionId(generateTransactionId());
        paymentRepo.save(payment);

        /*  FETCH PLAN */
        Plan plan = planRepo.findById(dto.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        /*  FETCH ACTIVE MEMBERSHIP (FAST & SAFE) */
        MemberMembership membership =
                membershipRepo.findByMemberIdAndStatus(
                        dto.getMemberId(),
                        MemberMembership.Status.ACTIVE
                ).orElse(null);

        if (membership != null) {

            /*  SAME PLAN → RENEW */
            if (membership.getPlan().getPlanId().equals(plan.getPlanId())) {
                membership.setEndDate(
                        membership.getEndDate().plusMonths(plan.getPlanDuration())
                );
            }
            /*  DIFFERENT PLAN → SWITCH */
            else {
                membership.setStatus(MemberMembership.Status.INACTIVE);

                membership = new MemberMembership();
                membership.setMemberId(dto.getMemberId());
                membership.setPlan(plan);
                membership.setStartDate(LocalDate.now());
                membership.setEndDate(
                        LocalDate.now().plusMonths(plan.getPlanDuration())
                );
                membership.setStatus(MemberMembership.Status.ACTIVE);
            }
        }
        /*  NO MEMBERSHIP */
        else {
            membership = new MemberMembership();
            membership.setMemberId(dto.getMemberId());
            membership.setPlan(plan);
            membership.setStartDate(LocalDate.now());
            membership.setEndDate(
                    LocalDate.now().plusMonths(plan.getPlanDuration())
            );
            membership.setStatus(MemberMembership.Status.ACTIVE);
        }

        membershipRepo.save(membership);

        /*  RESPONSE */
        PaymentResponseDTO response = new PaymentResponseDTO();
        response.setPaymentId(payment.getPaymentId());
        response.setMemberId(payment.getMid());
        response.setAmount(payment.getAmount());
        response.setPaymentDate(payment.getPaymentDate());
        response.setPaymentMethod(payment.getPaymentMethod());
        response.setTransactionId(payment.getTransactionId());
        response.setMembershipStatus(membership.getStatus().name());
        response.setPlanName(plan.getPlanName());

        return response;
    }
}