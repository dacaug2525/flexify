package com.flexify.admin.controllers;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.CreatePlanRequest;
import com.flexify.admin.dto.PlanDTO;
import com.flexify.admin.entities.Plan;
import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.services.PlanDiscountService;
import com.flexify.admin.services.PlanService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class PlanController {
	@Autowired
	private PlanService planService;

	 @GetMapping("/plans")
	    public List<Plan> getPlans() {
	        return planService.getAllPlans();
	   }
	
	// ================= Create Plan =================

	    @Autowired
	    private PlanDiscountService discountService;

	    // Existing create plan
	    @PostMapping("/plans/create")
	    public ResponseEntity<String> createPlan(@RequestBody CreatePlanRequest dto) {
	        try {
	            planService.createMembershipPlan(dto);
	            return ResponseEntity.ok("Plan created successfully");
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest().body("Error creating plan: " + e.getMessage());
	        }
	    }

	    // New endpoint to get all discounts
	    @GetMapping("/plans/discounts")
	    public ResponseEntity<List<PlanDiscount>> getAllDiscounts() {
	        List<PlanDiscount> discounts = discountService.getAll();
	        return ResponseEntity.ok(discounts);
	    }
	
	
	@GetMapping("/plans/{planId}")
	public ResponseEntity<?> getPlan(@PathVariable Integer planId) {
	    return ResponseEntity.ok(planService.getPlan(planId));
	}
	
	@GetMapping("/plans/{planId}/final-amount")
	public ResponseEntity<?> getFinalAmount(@PathVariable Integer planId) {
	    return ResponseEntity.ok(planService.getFinalAmount(planId));
	}
	
	/* ================= UPDATE PLAN ================= */
    @PutMapping("/plans/{id}")
    public ResponseEntity<Plan> updatePlan(
            @PathVariable Integer id,
            @RequestBody PlanDTO dto) {

        Plan updatedPlan = planService.updatePlan(id, dto);
        return ResponseEntity.ok(updatedPlan);
    }

    /* ================= DELETE PLAN ================= */
    @DeleteMapping("/plans/delete/{id}")
    public ResponseEntity<String> deletePlan(@PathVariable Integer id) {
        try {
            // Check if plan is referenced in plan_training
            boolean inUse = planService.isPlanInUse(id); // see below
            if (inUse) {
                return ResponseEntity
                        .badRequest()
                        .body("Cannot delete this plan because it is linked to trainings.");
            }

            // Safe to delete
            String message = planService.deletePlan(id);
            return ResponseEntity.ok(message);

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deleting plan: " + e.getMessage());
        }
    }



}
