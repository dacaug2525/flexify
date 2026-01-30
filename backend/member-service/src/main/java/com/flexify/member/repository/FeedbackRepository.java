package com.flexify.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexify.member.entities.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {
	List<Feedback> findByTid(Integer tid);
}
