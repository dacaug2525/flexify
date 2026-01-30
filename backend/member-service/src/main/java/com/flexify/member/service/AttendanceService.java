package com.flexify.member.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.AttendanceRequestDTO;
import com.flexify.member.entities.MemberAttendance;
import com.flexify.member.repository.MemberAttendanceRepository;

@Service
public class AttendanceService {
	
	

	    @Autowired
	    private MemberAttendanceRepository repo;

	    public MemberAttendance markAttendance(AttendanceRequestDTO dto) {
	        MemberAttendance att = new MemberAttendance();
	        att.setMid(dto.getMid());
	        att.setStatus(dto.getStatus());
	        att.setDate(LocalDateTime.now());
	        return repo.save(att);
	    }

	    public List<MemberAttendance> getAttendanceHistory(Integer mid) {
	        return repo.findByMid(mid);
	    }
	}


