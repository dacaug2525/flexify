package com.flexify.member.dto;

import com.flexify.member.entities.MemberAttendance;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class AttendanceRequestDTO {
	

	    @NotNull
	    private Integer mid;

	    @NotNull
	    private MemberAttendance.Status status;
	}


