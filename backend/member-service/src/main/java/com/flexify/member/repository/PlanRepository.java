package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.Plan;
@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {

}
