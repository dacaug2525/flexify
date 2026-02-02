package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.flexify.member.dto.TrainerAssignmentResponseDTO;
import com.flexify.member.service.TrainerAssignmentService;

@RestController
@RequestMapping("/flexify/member/trainer")
@CrossOrigin(origins = "http://localhost:3000")
public class TrainerAssignmentController {
	
	    @Autowired
	    private TrainerAssignmentService service;

	    /* ================= GET TRAINERS BY MEMBER ================= */
	    @GetMapping("/{mid}")
	    public ResponseEntity<List<TrainerAssignmentResponseDTO>>
	        getTrainersByMember(@PathVariable Integer mid) {

	        return ResponseEntity.ok(service.getTrainersByMember(mid));
	    }
	}


