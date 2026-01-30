package com.flexify.member.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberResponseDTO {
	

	    private Integer mid;
	    private int height;
	    private int weight;
	    private String address;
	    private String status;
	    private List<String> healthConditions;

	    // getters and setters
	}


