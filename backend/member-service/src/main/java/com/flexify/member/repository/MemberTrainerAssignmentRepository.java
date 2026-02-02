package com.flexify.member.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flexify.member.entities.MemberTrainerAssignment;

public interface MemberTrainerAssignmentRepository extends JpaRepository<MemberTrainerAssignment, Integer> {

    List<MemberTrainerAssignment> findByMember_Mid(Integer mid);
}
