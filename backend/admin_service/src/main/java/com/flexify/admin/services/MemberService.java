package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.dto.MemberDTO;
import com.flexify.admin.entities.Member;
import com.flexify.admin.exception.ResourceNotFoundException;
import com.flexify.admin.repositries.MemberRepository;


@Service
public class MemberService {
	
	@Autowired
    private MemberRepository memberRepository;

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
}
