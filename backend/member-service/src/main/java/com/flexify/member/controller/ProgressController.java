package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.member.dto.ProgressRequestDTO;
import com.flexify.member.service.ProgressService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("flexify/member/progress")
public class ProgressController {
	
	@Autowired
    private ProgressService progressService;

    // ✅ ADD PROGRESS
    @PostMapping("/add")
    public ResponseEntity<String> addProgress(
            @Valid @RequestBody ProgressRequestDTO dto) {

        progressService.addProgress(dto);
        return ResponseEntity.ok("Progress added successfully");
    }

    // ✅ GET PROGRESS HISTORY
    @GetMapping("/{mid}")
    public ResponseEntity<?> getProgress(@PathVariable Integer mid) {
        return ResponseEntity.ok(progressService.history(mid));
    }
}



