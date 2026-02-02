package com.flexify.member.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.flexify.member.dto.TrainerAssignmentResponseDTO;
import com.flexify.member.entities.MemberTrainerAssignment;
import com.flexify.member.repository.MemberTrainerAssignmentRepository;

@Service
public class TrainerAssignmentService {
	
	    @Autowired
	    private MemberTrainerAssignmentRepository assignmentRepo;

	    /* ================= FETCH TRAINERS BY MEMBER ================= */
	    public List<TrainerAssignmentResponseDTO> getTrainersByMember(Integer mid) {

	        List<MemberTrainerAssignment> assignments =
	                assignmentRepo.findByMember_Mid(mid);

	        return assignments.stream().map(a -> {
	            TrainerAssignmentResponseDTO dto = new TrainerAssignmentResponseDTO();

	            dto.setTrainerId(a.getTrainer().getTid());
	            dto.setTrainerName(
	                a.getTrainer().getUser().getFname() + " " +
	                a.getTrainer().getUser().getLname()
	            );
	            dto.setExperience(a.getTrainer().getExperience());
	            dto.setEmail(a.getTrainer().getUser().getEmail());
	            dto.setContact(a.getTrainer().getUser().getContact());
	            dto.setAssignedDate(a.getAssignDate());

	            return dto;
	        }).collect(Collectors.toList());
	    }
	

}
