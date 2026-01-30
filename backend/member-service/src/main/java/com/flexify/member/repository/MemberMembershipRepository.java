package com.flexify.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.MemberMembership;
@Repository
public interface MemberMembershipRepository extends JpaRepository<MemberMembership, Integer> {
	 // Find ACTIVE membership of member for a plan
    @Query("""
        SELECT m FROM MemberMembership m
        WHERE m.memberId = :memberId
        AND m.plan.planId = :planId
        AND m.status = 'ACTIVE'
    """)
    Optional<MemberMembership> findActiveMembership(
            @Param("memberId") Integer memberId,
            @Param("planId") Integer planId);
}
