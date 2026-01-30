package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flexify.member.service.MemberWorkoutService;
@RestController
@RequestMapping("/flexify/member/workout")
public class MemberWorkoutController {
	

	

	    @Autowired
	    private MemberWorkoutService workoutService;

	    // ✅ Member views workout schedule
	    @GetMapping("/{mid}")
	    public ResponseEntity<?> getWorkoutByMember(
	            @PathVariable Integer mid) {

	        return ResponseEntity.ok(
	                workoutService.getWorkoutByMember(mid)
	        );
	    }
	}


