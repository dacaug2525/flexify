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

import com.flexify.admin.entities.MemberTrainerAssignment;
import com.flexify.admin.services.MemberTrainerAssignmentService;
import com.flexify.admin.dto.AssignmentDTO;

@RestController
@RequestMapping("/flexify/admin/assignments")
@CrossOrigin(origins = "http://localhost:3000")
public class MemberTrainerAssignmentController {

	@Autowired
    private MemberTrainerAssignmentService assignmentService;

    @PostMapping("/assign-trainer")
    public ResponseEntity<?> assignTrainer(@RequestBody AssignmentRequest request) {
        try {
            MemberTrainerAssignment assignment = assignmentService.assignTrainer(request.getMid(), request.getTid());
            return ResponseEntity.ok(assignment);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // DTO for request
    public static class AssignmentRequest {
        private Integer mid;
        private Integer tid;

        public Integer getMid() { return mid; }
        public void setMid(Integer mid) { this.mid = mid; }
        public Integer getTid() { return tid; }
        public void setTid(Integer tid) { this.tid = tid; }
    }
    // List all assignments
    @GetMapping("/list")
    public List<AssignmentDTO> listAssignments() {
        return assignmentService.getAllAssignments();
    }
    
}
