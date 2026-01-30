package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.PlanTraining;
@Repository
public interface PlanTrainingRepository extends JpaRepository<PlanTraining, Integer> {

}
