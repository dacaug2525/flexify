package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.entities.Payment;
import com.flexify.admin.services.PaymentService;

@RestController
@RequestMapping("/admin")
public class PaymentController {
	@Autowired
	private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

 // GET all payments
    @GetMapping("/payments")
    public List<Payment> getAllPayments() {
        return service.getAllPayments();
    }
}
