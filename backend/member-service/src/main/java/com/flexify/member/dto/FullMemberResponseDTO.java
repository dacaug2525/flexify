package com.flexify.member.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FullMemberResponseDTO {
	

	    private Integer mid;
	    private LocalDate dob;
	    private int height;
	    private int weight;
	    private String address;
	    private String status;

	    private UserResponseDTO user;
	    private List<String> healthConditions;
	}


