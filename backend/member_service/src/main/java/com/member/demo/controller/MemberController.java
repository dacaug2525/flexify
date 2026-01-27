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

import com.member.demo.dto.MemberDTO;
import com.member.demo.dto.MemberUpdateDTO;
import com.member.demo.entities.Member;
import com.member.demo.service.MemberService;

@RestController
@RequestMapping("/flexify/members")
public class MemberController {
	

	    @Autowired
	    private MemberService service;

	    @PostMapping("/add")
	    public ResponseEntity<Member> create(@RequestBody MemberDTO dto) {
	        return ResponseEntity.ok(service.addMember(dto));
	    }

	    @GetMapping("getOne/{id}")
	    public ResponseEntity<Member> get(@PathVariable int id) {
	        return ResponseEntity.ok(service.getMember(id));
	    }

	    @GetMapping("/all")
	    public ResponseEntity<List<Member>> getAll() {
	        return ResponseEntity.ok(service.getAllMembers());
	    }

	    @PutMapping("/update/{id}")
	    public ResponseEntity<Member> update(
	            @PathVariable int id,
	            @RequestBody MemberUpdateDTO dto) {
	        return ResponseEntity.ok(service.updateMember(id, dto));
	    }

	    @DeleteMapping("delete/{id}")
	    public ResponseEntity<String> delete(@PathVariable int id) {
	        service.deleteMember(id);
	        return ResponseEntity.ok("Member deleted successfully");
	    }
	}

	


