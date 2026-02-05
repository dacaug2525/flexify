package com.flexify.admin.controllers;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.dto.MemberDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.services.MemberService;

//@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/admin")
public class MemberController {
	@Autowired
    private MemberService service;

	/*
    @PostMapping("/add")
    public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.addMember(dto));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }
    
    @GetMapping("/getAll")
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Integer id,
            @RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }
    
 // 

    // 🔹 DETAILS DATA
    @GetMapping("/details/{uid}")
    public MemberDetailDTO getDetails(@PathVariable Integer uid) {
        return memberService.getMemberDetails(uid);
    }
    */
	

    @GetMapping("/members/list")
    public List<Member> getMembers() {
        return service.getAllMembersForAdmin();
    }

    // MEMBER DETAILS
	 // Fetch member by user id
    @GetMapping("/members/details/{uid}")
    public ResponseEntity<MemberDTO> getMemberByUid(@PathVariable Integer uid) {
        return ResponseEntity.ok(service.getMemberByUid(uid));
    }
//    public MemberDTO getMemberByUid(@PathVariable Integer uid) {
//        return service.getMemberByUid(uid);
//    }

    // ADD MEMBER
    @PostMapping("/members/add")
    public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
        service.addMember(dto);
        return ResponseEntity.ok("Member added successfully");
    }
    
    @GetMapping("/members/plan/{planId}/trainings")
    public ResponseEntity<List<Map<String, Object>>> getMembersTrainings(@PathVariable int planId) {
        List<Map<String, Object>> list = service.getMembersTrainings(planId);
        return ResponseEntity.ok(list);
    }
}
