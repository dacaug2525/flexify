package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.flexify.member.dto.PaymentRequestDTO;
import com.flexify.member.service.PaymentService;

@RestController
@RequestMapping("/flexify/payment")

public class PaymentController {

	@Autowired
	private PaymentService service;

	// 💳 Purchase / Renew Membership
	@PostMapping("/pay")
	public ResponseEntity<?> makePayment(@Valid @RequestBody PaymentRequestDTO dto) {
		return ResponseEntity.ok(service.makePayment(dto));
	}

}
