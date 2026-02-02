package com.flexify.member.service;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.*;
import com.flexify.member.entities.*;
import com.flexify.member.repository.*;
@Service
public class MemberService {


	    @Autowired
	    private MemberRepository memberRepo;

	    @Autowired
	    private HealthConditionRepository healthRepo;

	    @Autowired
	    private MedInfoRepository medRepo;

	    @Autowired
	    private UserRepository userRepo;

	    // ✅ ADD MEMBER WITH MEDICAL INFO
	    public FullMemberResponseDTO addMemberWithMedical(MemberWithMedicalRequestDTO dto) {

	        User user = userRepo.findById(dto.getUid())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        Member member = new Member();
	        member.setDob(dto.getDob());
	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());
	        member.setUid(dto.getUid());
	        member.setJoinDate(LocalDateTime.now());
	        member.setStatus(Member.Status.active);

	        memberRepo.save(member);

	        if (dto.getMedicalInfo() != null) {
	            for (MedInfoDTO m : dto.getMedicalInfo()) {
	                HealthCondition hc = healthRepo.findById(m.getHealthId())
	                        .orElseThrow(() -> new RuntimeException("Health condition not found"));

	                MedInfo med = new MedInfo();
	                med.setMember(member);
	                med.setHealthCondition(hc);
	                med.setRemark(m.getRemark());

	                medRepo.save(med);
	            }
	        }
	        return mapToFullResponse(member, user);
	    }

	    // ✅ FETCH SINGLE MEMBER (FULL INFO)
	    public FullMemberResponseDTO getMember(Integer mid) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));

	        User user = userRepo.findById(member.getUid())
	                .orElseThrow(() -> new RuntimeException("User not found"));

	        return mapToFullResponse(member, user);
	    }

	    // ✅ FETCH ALL MEMBERS
	    public List<FullMemberResponseDTO> getAllMembers() {

	        return memberRepo.findAll()
	                .stream()
	                .map(m -> mapToFullResponse(
	                        m,
	                        userRepo.findById(m.getUid()).orElse(null)
	                ))
	                .toList();
	    }

	    // ✅ UPDATE MEMBER + MEDICAL INFO
	    public FullMemberResponseDTO updateMember(Integer mid, MemberWithMedicalRequestDTO dto) {

	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));
            member.setDob(dto.getDob());
	        member.setHeight(dto.getHeight());
	        member.setWeight(dto.getWeight());
	        member.setAddress(dto.getAddress());

	        medRepo.deleteAll(medRepo.findByMemberMid(mid));

	        if (dto.getMedicalInfo() != null) {
	            for (MedInfoDTO m : dto.getMedicalInfo()) {
	                HealthCondition hc = healthRepo.findById(m.getHealthId())
	                        .orElseThrow(() -> new RuntimeException("Health condition not found"));

	                MedInfo med = new MedInfo();
	                med.setMember(member);
	                med.setHealthCondition(hc);
	                med.setRemark(m.getRemark());

	                medRepo.save(med);
	            }
	        }

	        memberRepo.save(member);

	        User user = userRepo.findById(member.getUid()).orElse(null);
	        return mapToFullResponse(member, user);
	    }

	    // ✅ SOFT DELETE
	    public void deleteMember(Integer mid) {
	        Member member = memberRepo.findById(mid)
	                .orElseThrow(() -> new RuntimeException("Member not found"));
	        member.setStatus(Member.Status.inactive);
	        memberRepo.save(member);
	    }

	    // 🔁 MAPPER
	    private FullMemberResponseDTO mapToFullResponse(Member member, User user) {

	        FullMemberResponseDTO res = new FullMemberResponseDTO();
	        res.setMid(member.getMid());
	        res.setDob(member.getDob());
	        res.setHeight(member.getHeight());
	        res.setWeight(member.getWeight());
	        res.setAddress(member.getAddress());
	        res.setStatus(member.getStatus().name());

	        if (user != null) {
	            UserResponseDTO u = new UserResponseDTO();
	            u.setUid(user.getUid());
	            u.setUname(user.getUname());
	            u.setFname(user.getFname());
	            u.setLname(user.getLname());
	            u.setEmail(user.getEmail());
	            u.setContact(user.getContact());
	            u.setGender(user.getGender().name());
	            res.setUser(u);
	        }

	        res.setHealthConditions(
	                medRepo.findByMemberMid(member.getMid())
	                        .stream()
	                        .map(m -> m.getHealthCondition().getName())
	                        .toList()
	        );

	        return res;
	    }
	    
	 // ✅ FETCH MEMBER BY UID (FOR PROFILE SCREEN)
	    public FullMemberResponseDTO getMemberByUid(Integer uid) {

	        Member member = memberRepo.findByUid(uid).orElse(null);

	        if (member == null) {
	            return null; // frontend will know member not created yet
	        }

	        User user = userRepo.findById(uid).orElse(null);
	        return mapToFullResponse(member, user);
	    }
	    
	   
	    
	    
	}
