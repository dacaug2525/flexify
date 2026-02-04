package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flexify.admin.dto.AdminDashboardDTO;
import com.flexify.admin.dto.UserDTO;
import com.flexify.admin.services.AdminDashboardService;

@RestController
@RequestMapping("/flexify/admin/dashboard")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

	@Autowired
    private AdminDashboardService dashboardService;

    // Dashboard stats (total trainers & members)
    @GetMapping
    public ResponseEntity<AdminDashboardDTO> getStats() {
        return ResponseEntity.ok(dashboardService.getDashboardStats());
    }

    // Users with Trainer & Member role (Name, Email, Role only)
    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(dashboardService.getTrainerAndMemberUsers());
    }
 
}