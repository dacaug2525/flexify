package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.TrainingTable;
@Repository
public interface TrainingTableRepository extends JpaRepository<TrainingTable, Integer> {

}
