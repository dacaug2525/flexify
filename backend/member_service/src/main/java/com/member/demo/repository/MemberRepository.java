package com.member.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.member.demo.entities.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member,Integer > {
	 Optional<Member> findByUid(int uid);
	}

