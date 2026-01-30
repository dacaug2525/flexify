package com.flexify.member.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class MemberMembershipDTO {
	
	
	    @NotNull
	    private Integer memberId;

	    @NotNull
	    private Integer planId;

	    @NotNull
	    private LocalDate startDate;

	    @NotNull
	    private LocalDate endDate;

	    @NotNull
	    private String status; // ACTIVE or INACTIVE

	    // getters & setters
	

}
