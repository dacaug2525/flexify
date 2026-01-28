package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.entities.MemberTrainerAssignment;
import com.flexify.admin.repositries.MemberTrainerAssignmentRepository;

@Service
public class MemberTrainerAssignmentService {
	
	@Autowired
	private MemberTrainerAssignmentRepository repository;

    public MemberTrainerAssignmentService(MemberTrainerAssignmentRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public MemberTrainerAssignment addAssignment(MemberTrainerAssignment a) {
        return repository.save(a);
    }

    // READ ALL
    public List<MemberTrainerAssignment> getAllAssignments() {
        return repository.findAll();
    }

    // READ BY ID
    public MemberTrainerAssignment getAssignmentById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));
    }

    // UPDATE
    public MemberTrainerAssignment updateAssignment(Integer id, MemberTrainerAssignment a) {
        MemberTrainerAssignment existing = getAssignmentById(id);

        existing.setTid(a.getTid());
        existing.setMid(a.getMid());
        existing.setAssignDate(a.getAssignDate());

        return repository.save(existing);
    }

    // DELETE
    public void deleteAssignment(Integer id) {
        repository.deleteById(id);
    }
}
