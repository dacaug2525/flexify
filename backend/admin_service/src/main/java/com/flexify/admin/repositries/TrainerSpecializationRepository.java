package com.flexify.admin.repositries;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.TrainerSpecialization;

@Repository
public interface TrainerSpecializationRepository extends JpaRepository<TrainerSpecialization, Integer> {
	 List<TrainerSpecialization> findByTid(Integer tid);

	 List<TrainerSpecialization> findByTrId(Integer trId);
}
