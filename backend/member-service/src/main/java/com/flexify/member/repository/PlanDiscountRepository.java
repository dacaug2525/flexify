package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.PlanDiscount;
@Repository
public interface PlanDiscountRepository extends JpaRepository<PlanDiscount, Integer> {

}
