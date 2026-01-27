package com.flexify.admin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.DashboardDTO;
import com.flexify.admin.repositries.PlanDiscountRepository;
import com.flexify.admin.repositries.PlanRepository;

@RestController
@RequestMapping("/flexify/admin/dashboard")
public class AdminDashboardController {
	@Autowired
	public PlanRepository planRepo;
	
	@Autowired
	public PlanDiscountRepository discountRepo;
	
	
	@GetMapping
	public DashboardDTO dashboard() {
	    DashboardDTO dto = new DashboardDTO();
	    dto.setTotalPlans(planRepo.count());
	    dto.setTotalDiscounts(discountRepo.count());
	    return dto;
	}
}
