package com.flexify.admin.controllers;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.TrainerDetailsDTO;
import com.flexify.admin.services.TrainerService;

@RestController
@RequestMapping("/admin")
//@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {
	
		@Autowired
	    private TrainerService trainerService;
	
	    /* ================= TRAINER LIST ================= */
	    @GetMapping("/trainers/list")
	    public List<TrainerDetailsDTO> getAllTrainers() {
	        return trainerService.getAllTrainers();
	    }
	
	    /* ================= TRAINER DETAILS ================= */
	    @GetMapping("/trainers/details/{uid}")
	    public TrainerDetailsDTO getTrainerDetails(@PathVariable Integer uid) {
	        return trainerService.getTrainerDetails(uid);
	    }
	    
	 // Update salary
	    @PutMapping("/trainers/update-salary/{tid}")
	    public ResponseEntity<String> updateSalary(
	            @PathVariable Integer tid,
	            @RequestBody Map<String, Object> payload) {

	        if (!payload.containsKey("salary")) {
	            return ResponseEntity.badRequest().body("Salary is required");
	        }

	        BigDecimal salary;
	        try {
	            salary = new BigDecimal(payload.get("salary").toString());
	        } catch (NumberFormatException e) {
	            return ResponseEntity.badRequest().body("Invalid salary format");
	        }

	        trainerService.updateSalary(tid, salary);
	        return ResponseEntity.ok("Salary updated successfully");
	    }

	    
}
