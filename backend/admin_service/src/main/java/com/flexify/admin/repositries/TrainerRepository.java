package com.flexify.admin.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.flexify.admin.entities.Trainer;

@Repository
public interface TrainerRepository extends JpaRepository<Trainer, Integer> {

}
