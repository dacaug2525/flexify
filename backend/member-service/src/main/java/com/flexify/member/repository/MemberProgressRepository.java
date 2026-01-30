package com.flexify.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.MemberProgress;
@Repository
public interface MemberProgressRepository extends JpaRepository<MemberProgress, Integer> {
	 List<MemberProgress> findByMid(Integer mid);
}
