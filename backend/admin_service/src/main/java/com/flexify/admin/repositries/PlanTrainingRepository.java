package com.flexify.admin.repositries;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.PlanTraining;

@Repository
public interface PlanTrainingRepository extends JpaRepository<PlanTraining, Integer> {
	boolean existsByPlanId(Integer planId);
}
