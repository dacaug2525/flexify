package com.flexify.admin.repositries;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.flexify.admin.entities.MemberMembership;

@Repository
public interface MemberMembershipRepository extends JpaRepository<MemberMembership, Integer> {


    @Query("""
        SELECT m
        FROM MemberMembership m
        WHERE m.memberId = :memberId
          AND m.status = :status
    """)
    Optional<MemberMembership> findActiveByMemberId(
            @Param("memberId") Integer memberId,
            @Param("status") MemberMembership.Status status
    );
    
    List<MemberMembership> findByPlanId(int planId);
    
    
}
