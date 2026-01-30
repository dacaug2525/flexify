package com.flexify.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexify.member.entities.MemberAttendance;

public interface MemberAttendanceRepository extends JpaRepository<MemberAttendance, Integer> {
	List<MemberAttendance> findByMid(Integer mid);
}
