package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.entities.Payment;
import com.flexify.admin.services.PaymentService;

@RestController
@RequestMapping("/flexify/admin/payments")
public class PaymentController {
	@Autowired
	private PaymentService service;

    public PaymentController(PaymentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping("/add")
    public Payment add(@RequestBody Payment p) {
        return service.addPayment(p);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<Payment> getAll() {
        return service.getAllPayments();
    }

    // READ BY ID
    @GetMapping("/getById/{id}")
    public Payment getById(@PathVariable Integer id) {
        return service.getPaymentById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public Payment update(
            @PathVariable Integer id,
            @RequestBody Payment p) {
        return service.updatePayment(id, p);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        service.deletePayment(id);
        return "Payment deleted successfully";
    }
}
