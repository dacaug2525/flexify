package com.flexify.member.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;

import com.flexify.member.dto.*;
import com.flexify.member.entities.Plan;
import com.flexify.member.service.MembershipService;

@RestController
@RequestMapping("/flexify/member/membership")
public class MembershipController {

	

	    @Autowired
	    private MembershipService service;

	    // Add Plan
	    @PostMapping("/addplan")
	    public ResponseEntity<?> addPlan(@Valid @RequestBody PlanDTO dto) {
	        return ResponseEntity.ok(service.addPlan(dto));
	    }

	    // Get All Plans
	    @GetMapping("/plan")
	    public ResponseEntity<List<Plan>> getAllPlans() {
	        return ResponseEntity.ok(service.getAllPlans());
	    }

	    // Assign Membership to Member
	    @PostMapping("/assign")
	    public ResponseEntity<?> assignMembership(@Valid @RequestBody MemberMembershipDTO dto) {
	        return ResponseEntity.ok(service.assignMembership(dto));
	    }

	    // Get Membership by Member ID
	    @GetMapping("/{memberId}")
	    public ResponseEntity<?> getMemberMembership(@PathVariable Integer memberId) {
	        return ResponseEntity.ok(service.getMemberMembership(memberId));
	    }

	    // Update Membership
	    @PutMapping("/update/{id}")
	    public ResponseEntity<?> updateMembership(
	            @PathVariable Integer id,
	            @Valid @RequestBody MemberMembershipDTO dto) {
	        return ResponseEntity.ok(service.updateMembership(id, dto));
	    }

	    // Delete Membership
	    @DeleteMapping("/delete/{id}")
	    public ResponseEntity<?> deleteMembership(@PathVariable Integer id) {
	        service.deleteMembership(id);
	        return ResponseEntity.ok("Membership deleted successfully");
	    }
	    
	 // endpoint for detailed membership
	    @GetMapping("/detailed/{memberId}")
	    public ResponseEntity<?> getMemberMembershipDetailed(@PathVariable Integer memberId) {
	        return ResponseEntity.ok(service.getMemberMembershipDetailed(memberId));
	    }

	

}
