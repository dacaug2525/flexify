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

    // Fetch all payments
    public List<Payment> getAllPayments() {
        return repository.findAll();
    }

}
