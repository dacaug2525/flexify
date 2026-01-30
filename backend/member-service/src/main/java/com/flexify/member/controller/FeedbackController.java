package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.member.dto.FeedbackRequestDTO;
import com.flexify.member.service.FeedbackService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("flexify/member/feedback")
public class FeedbackController {
	
	
	    @Autowired
	    private FeedbackService service;

	    @PostMapping("/add")
	    public ResponseEntity<?> add(@Valid @RequestBody FeedbackRequestDTO dto) {
	        return ResponseEntity.ok(service.giveFeedback(dto));
	    }
	}


