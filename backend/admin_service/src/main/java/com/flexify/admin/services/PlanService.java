package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.PlanDTO;
import com.flexify.admin.entities.Plan;
import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.exception.ResourceNotFoundException;
import com.flexify.admin.repositries.PlanDiscountRepository;
import com.flexify.admin.repositries.PlanRepository;

@Service
public class PlanService {
		@Autowired
		private  PlanRepository planRepo;
		
		@Autowired
	    private  PlanDiscountRepository discountRepo;
	
		//PlanService constructor because planRepo and discountRepo no be null
		public PlanService(PlanRepository planRepo,PlanDiscountRepository discountRepo) {
			this.planRepo = planRepo;
			this.discountRepo = discountRepo;
		}
	
		//Add plan
		public Plan addPlan(PlanDTO dto) {
			PlanDiscount dis = discountRepo.findById(dto.getDisId())
				     .orElseThrow(() -> new ResourceNotFoundException("Discount not found"));
	
			Plan plan = new Plan();
			plan.setPlanName(dto.getPlanName());
			plan.setPlanDuration(dto.getPlanDuration());
			plan.setFees(dto.getFees());
			plan.setDescription(dto.getDescription());
			plan.setDiscount(dis);
			
			return planRepo.save(plan);
		}
	
		//Get plan by Id
		public Plan getPlan(Integer id) {
			return planRepo.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Plan not found"));
		}
	
		//list all plan
		public List<Plan> getAllPlans() {
			return planRepo.findAll();
		}
	
		//update plan
		public Plan updatePlan(Integer id, PlanDTO dto) {
			
			Plan plan = getPlan(id);
			plan.setPlanName(dto.getPlanName());
			plan.setPlanDuration(dto.getPlanDuration());
			plan.setFees(dto.getFees());
			plan.setDescription(dto.getDescription());
			
			return planRepo.save(plan);
		}
	
		//delete plan
		public String deletePlan(Integer id) {
			planRepo.deleteById(id);
			return "Plan discount deleted successfully";
		}
}
