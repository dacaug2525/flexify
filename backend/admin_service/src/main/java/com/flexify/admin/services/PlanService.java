package com.flexify.admin.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.CreatePlanRequest;
import com.flexify.admin.dto.PlanDTO;
import com.flexify.admin.entities.Plan;
import com.flexify.admin.entities.PlanDiscount;
import com.flexify.admin.repositries.PlanDiscountRepository;
import com.flexify.admin.repositries.PlanRepository;
import com.flexify.admin.repositries.PlanTrainingRepository;

import jakarta.transaction.Transactional;

@Service
public class PlanService {
	@Autowired
    private PlanRepository planRepo;

    @Autowired
    private PlanDiscountRepository discountRepo;

    @Autowired
    private PlanTrainingRepository planTrainingRepo;

  
    public Map<String, Object> getPlan(Integer planId) {

        List<Object[]> rows = planRepo.getPlanDetails(planId);

        if (rows.isEmpty()) {
            throw new RuntimeException("Plan not found");
        }

        Object[] firstRow = rows.get(0);

        BigDecimal fees = (BigDecimal) firstRow[3];
        BigDecimal discount = (BigDecimal) firstRow[5];

        BigDecimal finalAmount = calculateFinalAmount(fees, discount);

        List<Integer> trainerIds = new ArrayList<>();
        for (Object[] row : rows) {
            trainerIds.add(((Number) row[6]).intValue());
        }

        Map<String, Object> response = new HashMap<>();
        response.put("planId", firstRow[0]);
        response.put("planName", firstRow[1]);
        response.put("planDuration", firstRow[2]);
        response.put("fees", fees);
        response.put("description", firstRow[4]);
        response.put("discount", discount);
        response.put("finalAmount", finalAmount);
        response.put("trainerIds", trainerIds);

        return response;
    }


    
    
    @Transactional
    public void createMembershipPlan(CreatePlanRequest dto) {

        // 1️⃣ Validate discount
        if (dto.getDisId() == null) {
            throw new RuntimeException("Discount ID must be provided");
        }

        PlanDiscount discount = discountRepo.findById(dto.getDisId())
                .orElseThrow(() -> new RuntimeException("Discount not found for ID: " + dto.getDisId()));

        // 2️⃣ Create Plan entity
        Plan plan = new Plan();
        plan.setPlanName(dto.getPlanName());
        plan.setPlanDuration(dto.getPlanDuration());
        plan.setFees(dto.getFees());
        plan.setDescription(dto.getDescription());
        plan.setDiscount(discount);

        // 3️⃣ Save Plan
        planRepo.save(plan);
    }
    
	
		//list all plan
		public List<Plan> getAllPlans() {
			return planRepo.findAll();
		}
	
		// UPDATE PLAN
		public Plan updatePlan(Integer id, PlanDTO dto) {

		    Plan plan = planRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Plan not found with id: " + id));

		    plan.setPlanName(dto.getPlanName());
		    plan.setPlanDuration(dto.getPlanDuration());
		    plan.setFees(dto.getFees());
		    plan.setDescription(dto.getDescription());

		    return planRepo.save(plan);
		}

		/* ================= Delete Plan ================= */
		public String deletePlan(Integer id) {
		    Plan plan = planRepo.findById(id)
		            .orElseThrow(() -> new RuntimeException("Plan not found"));

		    planRepo.delete(plan);
		    return "Plan deleted successfully";
		}

		public boolean isPlanInUse(Integer planId) {
		    return planTrainingRepo.existsByPlanId(planId);
		}
		
		/* ================= PRICE CALCULATION ================= */

	    public BigDecimal calculateFinalAmount(BigDecimal fees, BigDecimal discount) {

	        return fees.subtract(
	                fees.multiply(discount)
	                     .divide(BigDecimal.valueOf(100))
	        );
	    }




	    public Map<String, BigDecimal> getFinalAmount(Integer planId) {

	        List<Object[]> row = planRepo.getPlanDetails(planId);

	        BigDecimal fees = (BigDecimal) row.get(0)[3];
	        BigDecimal discount = (BigDecimal) row.get(0)[5];

	        BigDecimal finalAmount = calculateFinalAmount(fees, discount);

	        Map<String, BigDecimal> map = new HashMap<>();
	        map.put("finalAmount", finalAmount);

	        return map;
	    }

}
