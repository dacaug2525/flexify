package com.flexify.admin.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.MemberTrainerAssignment;

@Repository
public interface MemberTrainerAssignmentRepository extends JpaRepository<MemberTrainerAssignment, Integer> {


	// Check same trainer already assigned to member
    Optional<MemberTrainerAssignment> findByMidAndTid(
            Integer mid,
            Integer tid
    );

    // List all trainers for a member
    List<MemberTrainerAssignment> findByMid(Integer mid);

    // List all members for a trainer
    List<MemberTrainerAssignment> findByTid(Integer tid);
    
 // Optional: Check if member already has trainer
    boolean existsByMidAndTid(Integer mid, Integer tid);

}
