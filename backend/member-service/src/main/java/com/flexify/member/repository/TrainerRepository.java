package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexify.member.entities.Trainer;

public interface TrainerRepository extends JpaRepository<Trainer, Integer> {
	Trainer findByUserUid(Integer uid);
}
