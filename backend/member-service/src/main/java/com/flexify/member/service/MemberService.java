package com.flexify.member.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.*;
import com.flexify.member.entities.HealthCondition;
import com.flexify.member.entities.MedInfo;
import com.flexify.member.entities.Member;
import com.flexify.member.repository.*;
@Service
public class MemberService {

	
	    @Autowired
	    private MemberRepository memberRepo;

	    @Autowired
	    private HealthConditionRepository healthRepo;

	    @Autowired
	    private MedInfoRepository medRepo;

	    public MemberResponseDTO addMember(MemberRequestDTO dto) {

	        Member member = new Member();
	        member.setDob(dto.getDob());
	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());
	        member.setUid(dto.getUid());
	        member.setJoinDate(LocalDateTime.now());
	        member.setStatus(Member.Status.active);

	        memberRepo.save(member);
	        return mapToResponse(member);
	    }

	    public void addMedicalInfo(Integer mid, MedInfoDTO dto) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        HealthCondition hc = healthRepo.findById(dto.getHealthId())
	                .orElseThrow(() -> new RuntimeException("Health condition not found"));

	        MedInfo med = new MedInfo();
	        med.setMember(member);
	        med.setHealthCondition(hc);
	        med.setRemark(dto.getRemark());

	        medRepo.save(med);
	    }

	    public MemberResponseDTO getMember(Integer mid) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        return mapToResponse(member);
	    }

	    public MemberResponseDTO updateMember(Integer mid, MemberRequestDTO dto) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());

	        memberRepo.save(member);
	        return mapToResponse(member);
	    }

	    public void deleteMember(Integer mid) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        member.setStatus(Member.Status.inactive);
	        memberRepo.save(member);
	    }

	    private MemberResponseDTO mapToResponse(Member member) {

	        MemberResponseDTO res = new MemberResponseDTO();
	        res.setMid(member.getMid());
	        res.setHeight(member.getHeight());
	        res.setWeight(member.getWeight());
	        res.setAddress(member.getAddress());
	        res.setStatus(member.getStatus().name());

	        List<String> conditions = medRepo.findByMemberMid(member.getMid())
	                .stream()
	                .map(m -> m.getHealthCondition().getName())
	                .toList();

	        res.setHealthConditions(conditions);
	        return res;
	    }
	}


