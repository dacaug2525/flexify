package com.flexify.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.entities.Trainer;
import com.flexify.member.repository.TrainerRepository;

@Service
public class TrainerService {
	
	    @Autowired
	    private TrainerRepository trainerRepo;

	    /*
	     * Get trainer assigned to a member
	     * (TEMP logic – replace with real mapping later)
	     */
	    public Trainer getTrainerByMember(Integer memberId) {

	        // ⚠ TEMP: until mapping exists
	        return trainerRepo.findAll()
	                .stream()
	                .findFirst()
	                .orElseThrow(() -> new RuntimeException("No trainer available"));
	    }
	}


