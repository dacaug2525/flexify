package com.flexify.admin.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.PlanDiscount;

@Repository
public interface PlanDiscountRepository extends JpaRepository<PlanDiscount, Integer> {

}
