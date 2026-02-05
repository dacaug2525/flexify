package com.flexify.admin.services;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.MemberDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.repositries.MemberRepository;


@Service
public class MemberService {
	
	@Autowired
    private MemberRepository memberRepo;

    // MEMBER DETAILS
	public MemberDTO getMemberByUid(Integer uid) {
        Optional<Member> memberOpt = memberRepo.findByUid(uid);
        
        Member member = memberRepo.findByUid(uid)
                .orElseThrow(() ->
                    new RuntimeException("Member not found for uid: " + uid)
                );

            MemberDTO dto = new MemberDTO();
            dto.setDob(member.getDob());
            dto.setHeight(member.getHeight());
            dto.setWeight(member.getWeight());
            dto.setAddress(member.getAddress());
            dto.setJoinDate(member.getJoinDate());
            dto.setStatus(member.getStatus());
            dto.setUid(member.getUid());

            return dto;
        }
        
        

//        if (memberOpt.isPresent()) {
//            Member member = memberOpt.get();
//            MemberDTO dto = new MemberDTO();
//            dto.setDob(member.getDob());
//            dto.setHeight(member.getHeight());
//            dto.setWeight(member.getWeight());
//            dto.setAddress(member.getAddress());
//            dto.setJoinDate(member.getJoinDate());
//            dto.setStatus(member.getStatus());
//            dto.setUid(member.getUid());
//            return dto;
//        } else {
//            return null; // or throw custom exception if you prefer
//        }
//    }

    // ADD MEMBER (after login)
    public void addMember(MemberDTO dto) {

        Member member = new Member();
        member.setDob(dto.getDob());
        member.setHeight(dto.getHeight());
        member.setWeight(dto.getWeight());
        member.setAddress(dto.getAddress());
        member.setJoinDate(dto.getJoinDate());
        member.setStatus(dto.getStatus());
        member.setUid(dto.getUid());

        memberRepo.save(member);
    }
    
    public List<Map<String, Object>> getMembersTrainings(int planId) {
        List<Object[]> rows = memberRepo.findMembersWithTrainingsAndTrainers(planId);
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : rows) {
            Map<String, Object> map = new HashMap<>();
            map.put("memberName", row[0]);
            map.put("trainerUserId", row[1]);
            map.put("trainerName", row[2]);
            map.put("specialization", row[3]);
            map.put("trainingId", row[4]);
            result.add(map);
        }

        return result;
    }

	public List<Member> getAllMembersForAdmin() {
		return memberRepo.findAll();
	}
}
