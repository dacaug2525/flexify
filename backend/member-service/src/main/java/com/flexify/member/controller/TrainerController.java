package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.member.service.TrainerService;

@RestController
@RequestMapping("/flexify/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerController {
	

	    @Autowired
	    private TrainerService trainerService;

	    @GetMapping("/by-member/{memberId}")
	    public ResponseEntity<?> getTrainerByMember(
	            @PathVariable Integer memberId) {

	        return ResponseEntity.ok(
	                trainerService.getTrainerByMember(memberId)
	        );
	    }
	}


