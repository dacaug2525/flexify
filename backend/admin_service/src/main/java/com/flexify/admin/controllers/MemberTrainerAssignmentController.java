package com.flexify.admin.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flexify.admin.entities.MemberTrainerAssignment;
import com.flexify.admin.services.MemberTrainerAssignmentService;

@RestController
@RequestMapping("/flexify/admin/member-trainer-assignments")
public class MemberTrainerAssignmentController {
	@Autowired
	private MemberTrainerAssignmentService service;

    public MemberTrainerAssignmentController(MemberTrainerAssignmentService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping("/assign")
    public MemberTrainerAssignment add(@RequestBody MemberTrainerAssignment a) {
        return service.addAssignment(a);
    }

    // READ ALL
    @GetMapping("/getAll")
    public List<MemberTrainerAssignment> getAll() {
        return service.getAllAssignments();
    }

    // READ BY ID
    @GetMapping("/getById/{id}")
    public MemberTrainerAssignment getById(@PathVariable Integer id) {
        return service.getAssignmentById(id);
    }

    // UPDATE
    @PutMapping("/updateAssign/{id}")
    public MemberTrainerAssignment update(
            @PathVariable Integer id,
            @RequestBody MemberTrainerAssignment a) {
        return service.updateAssignment(id, a);
    }

    // DELETE
    @DeleteMapping("/deleteAssign/{id}")
    public String delete(@PathVariable Integer id) {
        service.deleteAssignment(id);
        return "Member-Teacher assignment deleted successfully";
    }
}
