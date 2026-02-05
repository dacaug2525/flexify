package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flexify.member.service.MemberWorkoutService;
@RestController
@RequestMapping("/member")
//@CrossOrigin(origins="http://localhost:3000")
public class MemberWorkoutController {
	

	    @Autowired
	    private MemberWorkoutService workoutService;

	    //  Member views workout schedule
	    @GetMapping("/workout/{mid}")
	    public ResponseEntity<?> getWorkoutByMember(
	            @PathVariable Integer mid) {

	        return ResponseEntity.ok(
	                workoutService.getWorkoutByMember(mid)
	        );
	    }
	}


