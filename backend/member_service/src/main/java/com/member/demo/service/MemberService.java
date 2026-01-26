package com.member.demo.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.demo.entities.Member;
import com.member.demo.repository.MemberRepository;

@Service
public class MemberService {

	
	
	    @Autowired
	    MemberRepository memberRepository;

	    public Member createMember(Member member) {
	        
	        member.setJoin_Date(LocalDate.now());
	        member.setStatus("ACTIVE");
	        return memberRepository.save(member);
	    }

	    public Member getMemberById(int id) {
	        return memberRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Member not found"));
	    }

	    public List<Member> getAllMembers() {
	        return memberRepository.findAll();
	    }

	    public Member updateMember(int id, Member updatedMember) {
	        Member member = getMemberById(id);
            member.setDob(updatedMember.getDob());
	        member.setHeight(updatedMember.getHeight());
	        member.setWeight(updatedMember.getWeight());
	        member.setAddress(updatedMember.getAddress());
	        

	        return memberRepository.save(member);
	    }

	    public void deleteMember(int id) {
	        memberRepository.deleteById(id);
	    }

	  
	}

