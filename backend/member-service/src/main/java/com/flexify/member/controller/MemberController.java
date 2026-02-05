package com.flexify.member.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import jakarta.validation.Valid;

import com.flexify.member.dto.*;
import com.flexify.member.entities.HealthCondition;
import com.flexify.member.repository.HealthConditionRepository;
import com.flexify.member.service.MemberService;

@RestController
@RequestMapping("/member")
//@CrossOrigin(origins = "http://localhost:3000")
public class MemberController {

	    @Autowired
	    private MemberService memberService;

	    // ✅ ADD MEMBER WITH MEDICAL INFO
	    @PostMapping("/add-full")
	    public ResponseEntity<?> addMember(@Valid @RequestBody MemberWithMedicalRequestDTO dto) {
	        return ResponseEntity.ok(memberService.addMemberWithMedical(dto));
	    }

	    // ✅ GET SINGLE MEMBER
	    @GetMapping("/{mid}")
	    public ResponseEntity<?> getMember(@PathVariable Integer mid) {
	        return ResponseEntity.ok(memberService.getMember(mid));
	    }

	    // ✅ GET ALL MEMBERS
	    @GetMapping("/all")
	    public ResponseEntity<List<FullMemberResponseDTO>> getAll() {
	        return ResponseEntity.ok(memberService.getAllMembers());
	    }

	    // ✅ UPDATE MEMBER
	    @PutMapping("/update/{mid}")
	    public ResponseEntity<?> update(@PathVariable Integer mid,
	                                    @Valid @RequestBody MemberWithMedicalRequestDTO dto) {
	        return ResponseEntity.ok(memberService.updateMember(mid, dto));
	    }

	    // ✅ DELETE (SOFT)
	    @DeleteMapping("/delete/{mid}")
	    public ResponseEntity<?> delete(@PathVariable Integer mid) {
	        memberService.deleteMember(mid);
	        return ResponseEntity.ok("Member deactivated successfully");
	    }
	    
	    @Autowired
	    private HealthConditionRepository healthConditionRepository;

	    @GetMapping("/health-conditions")
	    public ResponseEntity<List<HealthCondition>> getHealthConditions() {
	        return ResponseEntity.ok(healthConditionRepository.findAll());
	    }
	    
	    @GetMapping("/by-user/{uid}")
	    public ResponseEntity<?> getByUser(@PathVariable Integer uid) {
	        return ResponseEntity.ok(memberService.getMemberByUid(uid));
	    }
	}
