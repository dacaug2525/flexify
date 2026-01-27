package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.entities.Payment;
import com.flexify.admin.repositries.PaymentRepository;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public Payment addPayment(Payment p) {
        return repository.save(p);
    }

    // READ ALL
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

    // READ BY ID
    public Payment getPaymentById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }

    // UPDATE
    public Payment updatePayment(Integer id, Payment p) {
        Payment existing = getPaymentById(id);

        existing.setMid(p.getMid());
        existing.setAmount(p.getAmount());
        existing.setPaymentDate(p.getPaymentDate());
        existing.setPaymentMethod(p.getPaymentMethod());
        existing.setTransactionId(p.getTransactionId());

        return repository.save(existing);
    }

    // DELETE
    public void deletePayment(Integer id) {
        repository.deleteById(id);
    }
}
