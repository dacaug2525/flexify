package com.flexify.member.dto;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class ProgressRequestDTO {
	
	    @NotNull
	    private Integer mid;

	    @Positive
	    private double weight;

	    @Positive
	    private double bmi;

	    private String remark;
	}


