package com.flexify.member.service;

import java.time.LocalDateTime;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.ProgressRequestDTO;
import com.flexify.member.entities.MemberProgress;
import com.flexify.member.repository.MemberProgressRepository;

@Service
public class ProgressService {
	
	
	    @Autowired
	    private MemberProgressRepository repo;

	    public MemberProgress addProgress(ProgressRequestDTO dto) {
	        MemberProgress p = new MemberProgress();
	        p.setMid(dto.getMid());
	        p.setWeight(dto.getWeight());
	        p.setBmi(dto.getBmi());
	        p.setRemark(dto.getRemark());
	        p.setRecordedDate(LocalDateTime.now());
	        return repo.save(p);
	    }

	    public List<MemberProgress> history(Integer mid) {
	        return repo.findByMid(mid);
	    }

	}


