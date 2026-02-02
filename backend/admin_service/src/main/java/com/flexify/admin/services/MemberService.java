package com.flexify.admin.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.MemberDTO;
import com.flexify.admin.dto.MemberDetailDTO;
import com.flexify.admin.dto.MemberListDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.entities.UserEntity;
import com.flexify.admin.exception.ResourceNotFoundException;
import com.flexify.admin.repositries.MemberRepository;
import com.flexify.admin.repositries.UserRepository;


@Service
public class MemberService {
	
	@Autowired
    private MemberRepository memberRepository;

	@Autowired
	private UserRepository userRepo;
	
	
    // CREATE
    public Member addMember(MemberDTO dto) {
        Member member = new Member();
        mapDtoToEntity(dto, member);
        return memberRepository.save(member);
    }

    // READ BY ID
    public Member getMemberById(Integer id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }

    // READ ALL
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // UPDATE
    public Member updateMember(Integer id, MemberDTO dto) {
        Member member = getMemberById(id);
        mapDtoToEntity(dto, member);
        return memberRepository.save(member);
    }

    // DELETE
    public void deleteMember(Integer id) {
        Member member = getMemberById(id);
        memberRepository.delete(member);
    }

    // helper
    private void mapDtoToEntity(MemberDTO dto, Member member) {
        member.setDob(dto.getDob());
        member.setHeight(dto.getHeight());
        member.setWeight(dto.getWeight());
        member.setAddress(dto.getAddress());
        member.setJoinDate(dto.getJoinDate());
        member.setStatus(dto.getStatus());
        member.setUid(dto.getUid());
    }
    
 // 🔹 TABLE VIEW
    public List<MemberListDTO> getAllMembersForAdmin() {

        List<Member> members = memberRepository.findAll();
        List<MemberListDTO> list = new ArrayList<>();

        for (Member m : members) {
        	UserEntity u = userRepo.findById(m.getUid()).orElse(null);
        	if (u == null) continue;

            MemberListDTO dto = new MemberListDTO();
            dto.setUid(u.getUid());
            dto.setUname(u.getUname());
            dto.setFname(u.getFname());
            dto.setLname(u.getLname());
            dto.setContact(u.getContact());
            dto.setJoinDate(m.getJoinDate());
            dto.setStatus(m.getStatus().name());

            list.add(dto);
        }
        return list;
    }

    // 🔹 DETAILS VIEW
    public MemberDetailDTO getMemberDetails(Integer uid) {

        Member m = memberRepository.findByUid(uid)
                .orElseThrow(() -> new RuntimeException("Member not found"));

        UserEntity u = userRepo.findById(m.getUid())
                .orElseThrow(() -> new RuntimeException("User not found"));

        MemberDetailDTO dto = new MemberDetailDTO();
        dto.setMid(m.getMid());
        dto.setEmail(u.getEmail());
        dto.setDob(m.getDob());
        dto.setHeight(m.getHeight());
        dto.setWeight(m.getWeight());
        dto.setAddress(m.getAddress());
        dto.setGender(u.getGender());

        return dto;
    }
}
