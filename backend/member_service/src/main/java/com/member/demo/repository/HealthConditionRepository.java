package com.member.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.demo.entities.HealthCondition;

@Repository
public interface HealthConditionRepository extends JpaRepository<HealthCondition, Integer> {

}
