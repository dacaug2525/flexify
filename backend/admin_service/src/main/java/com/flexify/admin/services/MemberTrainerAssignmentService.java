package com.flexify.admin.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.AssignmentDTO;

import com.flexify.admin.entities.MemberTrainerAssignment;
import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.repositries.MemberTrainerAssignmentRepository;
import com.flexify.admin.repositries.UserRepository;

@Service
public class MemberTrainerAssignmentService {
	
	 @Autowired
	    private MemberTrainerAssignmentRepository assignmentRepo;

	 @Autowired
	 private UserRepository userRepo;
	 
	    public MemberTrainerAssignment assignTrainer(Integer mid, Integer tid) throws Exception {
	        // Check if assignment already exists
	        if (assignmentRepo.existsByMidAndTid(mid, tid)) {
	            throw new Exception("This member is already assigned to this trainer");
	        }

	        MemberTrainerAssignment assignment = new MemberTrainerAssignment();
	        assignment.setMid(mid);
	        assignment.setTid(tid);
	        assignment.setAssignDate(LocalDateTime.now());

	        return assignmentRepo.save(assignment);
	    }
	    
	    
    
	    public List<AssignmentDTO> getAllAssignments() {
	        List<MemberTrainerAssignment> assignments = assignmentRepo.findAll();

	        return assignments.stream().map(a -> {
	            UserEntity member = userRepo.findById(a.getMid()).orElse(null);
	            UserEntity trainer = userRepo.findById(a.getTid()).orElse(null);

	            String memberName = member != null ? member.getFname() + " " + member.getLname() : "N/A";
	            String trainerName = trainer != null ? trainer.getFname() + " " + trainer.getLname() : "N/A";

	            return new AssignmentDTO(
	                    a.getAssignmentId(),
	                    a.getMid(),
	                    a.getTid(),
	                    memberName,
	                    trainerName,
	                    a.getAssignDate()
	            );
	        }).collect(Collectors.toList());
	    }
}
