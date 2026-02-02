package com.flexify.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.AdminDashboardDTO;
import com.flexify.admin.services.AdminDashboardService;

@RestController
@RequestMapping("/flexify/admin/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    @Autowired
    private AdminDashboardService dashboardService;

    @GetMapping
    public ResponseEntity<AdminDashboardDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }
 
}