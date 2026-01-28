package com.flexify.admin.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.MemberTrainerAssignment;

@Repository
public interface MemberTrainerAssignmentRepository extends JpaRepository<MemberTrainerAssignment, Integer> {

}
