package com.flexify.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.flexify.member.dto.AttendanceRequestDTO;
import com.flexify.member.entities.MemberAttendance;
import com.flexify.member.service.AttendanceService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/member")
//@CrossOrigin(origins = "http://localhost:3000")
public class AttendanceController {

    @Autowired
    private AttendanceService service;

    /* ================= MARK ================= */
    @PostMapping("/attendance/mark")
    public ResponseEntity<?> mark(@Valid @RequestBody AttendanceRequestDTO dto) {
        return ResponseEntity.ok(service.markAttendance(dto));
    }

    /* ================= HISTORY ================= */
    @GetMapping("/attendence/{mid}")
    public ResponseEntity<?> history(@PathVariable Integer mid) {
        return ResponseEntity.ok(service.getAttendanceHistory(mid));
    }

    /* ================= COUNT ================= */
    @GetMapping("/attendence/count/{mid}/{status}")
    public ResponseEntity<?> count(
            @PathVariable Integer mid,
            @PathVariable MemberAttendance.Status status) {
        return ResponseEntity.ok(service.getCount(mid, status));
    }
}
