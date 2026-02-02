package com.flexify.admin.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.AssignTrainerRequestDTO;
import com.flexify.admin.dto.AssignTrainerResponseDTO;
import com.flexify.admin.dto.AssignmentViewDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.entities.MemberTrainerAssignment;
import com.flexify.admin.entities.Trainer;
import com.flexify.admin.repositries.MemberRepository;
import com.flexify.admin.repositries.MemberTrainerAssignmentRepository;
import com.flexify.admin.repositries.TrainerRepository;

@Service
public class MemberTrainerAssignmentService {
	
	@Autowired
    private MemberTrainerAssignmentRepository assignmentRepo;

    @Autowired
    private TrainerRepository trainerRepo;

    @Autowired
    private MemberRepository memberRepo;

    public AssignTrainerResponseDTO assignTrainerToMember(
            AssignTrainerRequestDTO dto) {

        // 1️⃣ Validate trainer exists
        Trainer trainer = trainerRepo.findById(dto.getTid())
                .orElseThrow(() ->
                        new RuntimeException("Trainer not found"));

        // 2️⃣ Validate member exists
        Member member = memberRepo.findById(dto.getMid())
                .orElseThrow(() ->
                        new RuntimeException("Member not found"));

        // 3️⃣ Check if member already has a trainer
        assignmentRepo.findByMid(dto.getMid())
                .ifPresent(a -> {
                    throw new RuntimeException(
                        "Trainer already assigned to this member");
                });

        // 4️⃣ Create assignment
        MemberTrainerAssignment assignment =
                new MemberTrainerAssignment();

        assignment.setTid(dto.getTid());
        assignment.setMid(dto.getMid());
        assignment.setAssignDate(LocalDateTime.now());

        MemberTrainerAssignment saved =
                assignmentRepo.save(assignment);

        // 5️⃣ Build response
        AssignTrainerResponseDTO response =
                new AssignTrainerResponseDTO();

        response.setAssignmentId(saved.getAssignmentId());
        response.setTid(saved.getTid());
        response.setMid(saved.getMid());
        response.setAssignDate(saved.getAssignDate());

        return response;
    }
    

    public List<AssignmentViewDTO> getAllAssignments() {

        List<MemberTrainerAssignment> list = assignmentRepo.findAll();
        List<AssignmentViewDTO> result = new ArrayList<>();

        for (MemberTrainerAssignment a : list) {
            AssignmentViewDTO dto = new AssignmentViewDTO();
            dto.setAssignmentId(a.getAssignmentId());
            dto.setMid(a.getMid());
            dto.setTid(a.getTid());
            dto.setAssignDate(a.getAssignDate());

            result.add(dto);
        }

        return result;
    }
}
