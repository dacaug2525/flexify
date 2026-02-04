package com.flexify.admin.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.Plan;
import com.flexify.admin.entities.PlanTraining;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

	 @Query(value = """
		        SELECT 
		            p.plan_id,
		            p.plan_name,
		            p.plan_duration,
		            p.fees,
		            p.description,
		            d.discount,
		            pt.tr_id
		        FROM plan p
		        JOIN plan_discount d ON p.dis_id = d.dis_id
		        LEFT JOIN plan_training pt ON p.plan_id = pt.plan_id
		        WHERE p.plan_id = :planId
		        """, nativeQuery = true)
	 
	List<Object[]> getPlanDetails(@Param("planId") Integer planId);
	
	
	List<PlanTraining> findByPlanId(int planId);
   
}
