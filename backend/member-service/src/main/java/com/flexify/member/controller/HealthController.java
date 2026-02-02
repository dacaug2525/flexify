package com.flexify.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.member.entities.HealthCondition;
import com.flexify.member.repository.HealthConditionRepository;

@RestController
@RequestMapping("/flexify/health")
@CrossOrigin(origins = "http://localhost:3000")
public class HealthController {
	
	

	    @Autowired
	    private HealthConditionRepository repo;

	    @GetMapping("/all")
	    public List<HealthCondition> getAll() {
	        return repo.findAll();
	    }
	}


