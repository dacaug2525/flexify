package com.flexify.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flexify.member.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {

}
