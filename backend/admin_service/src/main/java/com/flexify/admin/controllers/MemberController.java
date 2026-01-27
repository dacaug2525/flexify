package com.flexify.admin.controllers;

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

import com.flexify.admin.dto.MemberDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.services.MemberService;

@RestController
@RequestMapping("/flexify/admin/members")
public class MemberController {
	@Autowired
    private MemberService memberService;

    @PostMapping
    public ResponseEntity<?> addMember(@RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.addMember(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Member> getMember(@PathVariable Integer id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @GetMapping
    public ResponseEntity<List<Member>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Member> updateMember(
            @PathVariable Integer id,
            @RequestBody MemberDTO dto) {
        return ResponseEntity.ok(memberService.updateMember(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Integer id) {
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member deleted successfully");
    }
}
