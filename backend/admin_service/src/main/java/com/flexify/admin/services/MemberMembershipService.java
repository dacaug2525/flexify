package com.flexify.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.admin.entities.MemberMembership;
import com.flexify.admin.repositries.MemberMembershipRepository;

@Service
public class MemberMembershipService {
	
	@Autowired
	private MemberMembershipRepository repository;

    public MemberMembershipService(MemberMembershipRepository repository) {
        this.repository = repository;
    }

    // CREATE
    public MemberMembership addMembership(MemberMembership m) {
        return repository.save(m);
    }

    // get ALL
    public List<MemberMembership> getAllMemberships() {
        return repository.findAll();
    }

    // get BY ID
    public MemberMembership getMembershipById(Integer id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membership not found"));
    }

    // UPDATE
    public MemberMembership updateMembership(Integer id, MemberMembership m) {
        MemberMembership existing = getMembershipById(id);

        existing.setMemberId(m.getMemberId());
        existing.setPlanId(m.getPlanId());
        existing.setStartDate(m.getStartDate());
        existing.setEndDate(m.getEndDate());
        existing.setStatus(m.getStatus());

        return repository.save(existing);
    }
    
    // DELETE
    public void deleteMembership(Integer id) {
        repository.deleteById(id);
    }
}
