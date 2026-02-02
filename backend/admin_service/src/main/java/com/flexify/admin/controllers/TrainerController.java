package com.flexify.admin.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.TrainerDetailsDTO;
import com.flexify.admin.services.TrainerService;

@RestController
@RequestMapping("/flexify/admin/trainers")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {
	
		@Autowired
	    private TrainerService trainerService;
	
	    /* ================= TRAINER LIST ================= */
	    @GetMapping("/list")
	    public List<TrainerDetailsDTO> getAllTrainers() {
	        return trainerService.getAllTrainers();
	    }
	
	    /* ================= TRAINER DETAILS ================= */
	    @GetMapping("/details/{uid}")
	    public TrainerDetailsDTO getTrainerDetails(@PathVariable Integer uid) {
	        return trainerService.getTrainerDetails(uid);
	    }
	    
}
