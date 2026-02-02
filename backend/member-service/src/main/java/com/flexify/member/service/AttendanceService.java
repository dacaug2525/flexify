package com.flexify.member.service;

import java.time.LocalDate;
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

	    /* ================= MARK ATTENDANCE ================= */
	    public MemberAttendance markAttendance(AttendanceRequestDTO dto) {

	        LocalDate today = LocalDate.now();

	        boolean alreadyMarked = repo.existsByMidAndDateBetween(
	            dto.getMid(),
	            today.atStartOfDay(),
	            today.plusDays(1).atStartOfDay()
	        );

	        if (alreadyMarked) {
	            throw new RuntimeException("Attendance already marked for today");
	        }

	        MemberAttendance att = new MemberAttendance();
	        att.setMid(dto.getMid());
	        att.setStatus(dto.getStatus());
	        att.setDate(LocalDateTime.now());

	        return repo.save(att);
	    }

	    /* ================= HISTORY ================= */
	    public List<MemberAttendance> getAttendanceHistory(Integer mid) {
	        return repo.findByMid(mid);
	    }

	    /* ================= COUNT ================= */
	    public long getCount(Integer mid, MemberAttendance.Status status) {
	        return repo.countByMidAndStatus(mid, status);
	    }
	}



