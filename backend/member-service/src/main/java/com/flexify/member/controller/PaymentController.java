package com.flexify.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.flexify.member.dto.PaymentRequestDTO;
import com.flexify.member.entities.Payment;
import com.flexify.member.repository.PaymentRepository;
import com.flexify.member.service.PaymentService;

@RestController
@RequestMapping("/flexify/payment")
@CrossOrigin(origins="http://localhost:3000")
public class PaymentController {

	@Autowired
	private PaymentService service;
    @Autowired
    private PaymentRepository paymentRepo;
	// 💳 Purchase / Renew Membership
	@PostMapping("/pay")
	public ResponseEntity<?> makePayment(@Valid @RequestBody PaymentRequestDTO dto) {
		return ResponseEntity.ok(service.makePayment(dto));
	}
	
	@GetMapping("/{memberId}")
	public List<Payment> getPaymentsByMember(@PathVariable Integer memberId) {
	    return paymentRepo.findByMid(memberId);
	}

}
