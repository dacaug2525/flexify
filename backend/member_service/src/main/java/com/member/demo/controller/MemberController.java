package com.member.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.member.demo.entities.Member;
import com.member.demo.service.MemberService;

@RestController
@RequestMapping("/flexify/members")
public class MemberController {
	
	    @Autowired
	    private MemberService memberService;

	    // CREATE
	    @PostMapping
	    public ResponseEntity<Member> createMember(
	            @RequestBody Member member) {
	        return ResponseEntity.ok(memberService.createMember(member));
	    }

	    // READ by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<Member> getMemberById(
	            @PathVariable int id) {
	        return ResponseEntity.ok(memberService.getMemberById(id));
	    }

	    // READ ALL
	    @GetMapping
	    public ResponseEntity<List<Member>> getAllMembers() {
	        return ResponseEntity.ok(memberService.getAllMembers());
	    }

	    // UPDATE
	    @PutMapping("/{id}")
	    public ResponseEntity<Member> updateMember(
	            @PathVariable int id,
	            @RequestBody Member member) {
	        return ResponseEntity.ok(
	                memberService.updateMember(id, member));
	    }

	    // DELETE
	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteMember(
	            @PathVariable int id) {
	        memberService.deleteMember(id);
	        return ResponseEntity.ok("Member deleted successfully");
	    }
	}


