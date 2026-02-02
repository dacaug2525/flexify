package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.flexify.admin.dto.AssignmentViewDTO;
import com.flexify.admin.dto.AssignTrainerRequestDTO;
import com.flexify.admin.dto.AssignTrainerResponseDTO;
import com.flexify.admin.services.MemberTrainerAssignmentService;

@RestController
@RequestMapping("/flexify/admin/assignments")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberTrainerAssignmentController {

    @Autowired
    private MemberTrainerAssignmentService assignmentService;

    @PostMapping("/assign-trainer")
    public ResponseEntity<?> assignTrainer(
            @RequestBody AssignTrainerRequestDTO dto) {

        AssignTrainerResponseDTO response =
                assignmentService.assignTrainerToMember(dto);

        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<AssignmentViewDTO>> getAllAssignments() {
        return ResponseEntity.ok(assignmentService.getAllAssignments());
    }
    
}
