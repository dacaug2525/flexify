package com.flexify.member.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.flexify.member.entities.*;
import com.flexify.member.dto.*;
import com.flexify.member.repository.*;
import java.util.List;
import java.time.LocalDate;
import java.util.Optional;
import com.flexify.member.dto.MemberMembershipResponseDTO;
import java.util.stream.Collectors;

@Service
public class MembershipService {
	


	    @Autowired
	    private PlanRepository planRepo;

	    @Autowired
	    private PlanDiscountRepository discountRepo;

	    @Autowired
	    private TrainingTableRepository trainingRepo;

	    @Autowired
	    private PlanTrainingRepository planTrainingRepo;

	    @Autowired
	    private MemberMembershipRepository memberMembershipRepo;

	    // Add Plan
	    public Plan addPlan(PlanDTO dto) {
	        PlanDiscount discount = discountRepo.findById(dto.getDiscountId())
	                .orElseThrow(() -> new RuntimeException("Discount not found"));
	        Plan plan = new Plan();
	        plan.setPlanName(dto.getPlanName());
	        plan.setPlanDuration(dto.getPlanDuration());
	        plan.setFees(dto.getFees());
	        plan.setDescription(dto.getDescription());
	        plan.setDiscount(discount);
	        return planRepo.save(plan);
	    }

	    // Get all plans
	    public List<Plan> getAllPlans() {
	        return planRepo.findAll();
	    }

	    // Assign Membership to Member
	    public MemberMembership assignMembership(MemberMembershipDTO dto) {
	        Plan plan = planRepo.findById(dto.getPlanId())
	                .orElseThrow(() -> new RuntimeException("Plan not found"));
	        MemberMembership mm = new MemberMembership();
	        mm.setMemberId(dto.getMemberId());
	        mm.setPlan(plan);
	        mm.setStartDate(dto.getStartDate());
	        mm.setEndDate(dto.getEndDate());
	        mm.setStatus(MemberMembership.Status.valueOf(dto.getStatus().toUpperCase()));
	        return memberMembershipRepo.save(mm);
	    }

	    // View Member Membership
	    public List<MemberMembership> getMemberMembership(Integer memberId) {
	        return memberMembershipRepo.findAll()
	                .stream()
	                .filter(mm -> mm.getMemberId().equals(memberId))
	                .toList();
	    }

	    // Update Membership
	    public MemberMembership updateMembership(Integer id, MemberMembershipDTO dto) {
	        MemberMembership mm = memberMembershipRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Membership not found"));
	        mm.setStartDate(dto.getStartDate());
	        mm.setEndDate(dto.getEndDate());
	        mm.setStatus(MemberMembership.Status.valueOf(dto.getStatus().toUpperCase()));
	        return memberMembershipRepo.save(mm);
	    }

	    // Delete Membership
	    public void deleteMembership(Integer id) {
	        memberMembershipRepo.deleteById(id);
	    }
	    
	
	   

	    public List<MemberMembershipResponseDTO> getMemberMembershipDetailed(Integer memberId) {
	        List<MemberMembership> memberships = memberMembershipRepo.findAll()
	                .stream()
	                .filter(mm -> mm.getMemberId().equals(memberId))
	                .toList();

	        return memberships.stream().map(mm -> {
	            MemberMembershipResponseDTO dto = new MemberMembershipResponseDTO();
	            dto.setMembershipId(mm.getMembershipId());
	            dto.setMemberId(mm.getMemberId());
	            dto.setStatus(mm.getStatus().name());

	            Plan plan = mm.getPlan();
	            dto.setPlanName(plan.getPlanName());
	            dto.setPlanDuration(plan.getPlanDuration());
	            dto.setFees(plan.getFees());
	            dto.setPlanDescription(plan.getDescription());

	            if (plan.getDiscount() != null) {
	                dto.setDiscountDuration(plan.getDiscount().getDuration());
	                dto.setDiscountAmount(plan.getDiscount().getDiscount());
	            }

	            // Training names
	            List<String> trainingNames = plan.getPlanTrainings()
	                    .stream()
	                    .map(pt -> pt.getTraining().getTrName())
	                    .toList();
	            dto.setTrainingNames(trainingNames);

	            return dto;
	        }).toList();
	    }

	}


