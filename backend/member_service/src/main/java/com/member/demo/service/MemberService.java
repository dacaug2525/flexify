package com.member.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.member.demo.dto.MedInfoDTO;
import com.member.demo.dto.MemberDTO;
import com.member.demo.dto.MemberUpdateDTO;
import com.member.demo.entities.HealthCondition;
import com.member.demo.entities.MedicalInfo;
import com.member.demo.entities.Member;
import com.member.demo.repository.HealthConditionRepository;
import com.member.demo.repository.MemberRepository;

@Service
public class MemberService {


	    @Autowired
	    private MemberRepository memberRepo;
	    
	    @Autowired
	    private HealthConditionRepository healthRepo;

	    // CREATE
	    public Member addMember(MemberDTO dto) {

	        Member member = new Member();
	        member.setUid(dto.getUid());
	        member.setDob(dto.getDob());
	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());
	        member.setJoin_Date(LocalDate.now());
	       // member.setStatus(dto.getStatus());
	        
	        // joinDate auto
//	        member.setJoinDate(
//	            dto.getJoinDate() != null ? dto.getJoinDate() : LocalDate.now()
//	        );
	        
	        if (dto.getStatus() != null && !dto.getStatus().isBlank()) {
	            member.setStatus(dto.getStatus().toUpperCase());
	        } else {
	            member.setStatus("ACTIVE");
	        }


	        List<MedicalInfo> medList = new ArrayList<>();

	        if (dto.getHealthConditions() != null) {
	            for (MedInfoDTO m : dto.getHealthConditions()) {

	                HealthCondition hc = healthRepo.findById(m.getHealth_id())
	                        .orElseThrow(() -> new RuntimeException("Health condition not found"));

	                MedicalInfo med = new MedicalInfo();
	                med.setHealthCondition(hc);
	                med.setRemark(m.getRemark());
	                med.setMember(member); //  THIS SETS mid AUTOMATICALLY in med_info table

	                medList.add(med);
	            }
	        }

	        member.setMedInfos(medList);

	        //mid auto-generated here
	        return memberRepo.save(member);
	    }

	    // GET BY ID
	    public Member getMember(int id) {
	        return memberRepo.findById(id)
	                .orElseThrow(() -> new RuntimeException("Member not found"));
	    }

	    // GET ALL
	    public List<Member> getAllMembers() {
	        return memberRepo.findAll();
	    }

	    // UPDATE
	    /*
	    public Member updateMember(int id, MemberDTO dto) {
	        Member member = getMember(id);

	        member.setDob(dto.getDob());
	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());
	        member.setStatus(dto.getStatus());

	        return memberRepo.save(member);
	    }*/
	    public Member updateMember(int mid, MemberUpdateDTO dto) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        if (dto.getDob() != null)
	            member.setDob(dto.getDob());

	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        
	        if (dto.getAddress() != null)
	            member.setAddress(dto.getAddress());

	        //  Update health conditions 
	        if (dto.getHealthConditions() != null) {
	            member.getMedInfos().clear();

	            for (MedInfoDTO m : dto.getHealthConditions()) {
	                HealthCondition hc = healthRepo.findById(m.getHealth_id())
	                        .orElseThrow(() -> new RuntimeException("Health condition not found"));

	                MedicalInfo med = new MedicalInfo();
	                med.setHealthCondition(hc);
	                med.setRemark(m.getRemark());
	                med.setMember(member);

	                member.getMedInfos().add(med);
	            }
	        }

	        return memberRepo.save(member);
	    }


	    // DELETE
	    public void deleteMember(int id) {
	        memberRepo.deleteById(id);
	    }
	}
