package com.flexify.admin.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.MemberTrainerAssignment;

@Repository
public interface MemberTrainerAssignmentRepository extends JpaRepository<MemberTrainerAssignment, Integer> {

    // Check if trainer already assigned to member
    Optional<MemberTrainerAssignment> findByMid(Integer mid);

    // Optional: list by trainer
    List<MemberTrainerAssignment> findByTid(Integer tid);
}
