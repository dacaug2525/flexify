package com.flexify.member.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.flexify.member.dto.*;
import com.flexify.member.service.MemberService;

@RestController
@RequestMapping("/flexify/member")
public class MemberController {
	

	
	

	    @Autowired
	    private MemberService memberService;

	    @PostMapping("/add")
	    public ResponseEntity<?> addMember(@Valid @RequestBody MemberRequestDTO dto) {
	        return ResponseEntity.ok(memberService.addMember(dto));
	    }

	    @PostMapping("/add/{mid}/medical")
	    public ResponseEntity<?> addMedical(
	            @PathVariable Integer mid,
	            @Valid @RequestBody MedInfoDTO dto) {

	        memberService.addMedicalInfo(mid, dto);
	        return ResponseEntity.ok("Medical info added successfully");
	    }

	    @GetMapping("/{mid}")
	    public ResponseEntity<?> getMember(@PathVariable Integer mid) {
	        return ResponseEntity.ok(memberService.getMember(mid));
	    }

	    @PutMapping("/update/{mid}")
	    public ResponseEntity<?> updateMember(
	            @PathVariable Integer mid,
	            @Valid @RequestBody MemberRequestDTO dto) {

	        return ResponseEntity.ok(memberService.updateMember(mid, dto));
	    }

	    @DeleteMapping("/delete/{mid}")
	    public ResponseEntity<?> deleteMember(@PathVariable Integer mid) {
	        memberService.deleteMember(mid);
	        return ResponseEntity.ok("Member deactivated successfully");
	    }
	}


