package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.HealthCondition;

@Repository
public interface HealthConditionRepository extends JpaRepository<HealthCondition, Integer> {

}
