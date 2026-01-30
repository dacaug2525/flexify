package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.member.dto.AttendanceRequestDTO;
import com.flexify.member.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("flexify/member/attendence")
public class AttendanceController {
	


	    @Autowired
	    private AttendanceService service;

	    @PostMapping("/mark")
	    public ResponseEntity<?> mark(@Valid @RequestBody AttendanceRequestDTO dto) {
	        return ResponseEntity.ok(service.markAttendance(dto));
	    }

	    @GetMapping("/{mid}")
	    public ResponseEntity<?> history(@PathVariable Integer mid) {
	        return ResponseEntity.ok(service.getAttendanceHistory(mid));
	    }
	}


