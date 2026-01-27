package com.flexify.admin.repositries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.admin.entities.MemberMembership;

@Repository
public interface MemberMembershipRepository extends JpaRepository<MemberMembership, Integer> {

}
