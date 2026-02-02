package com.flexify.member.dto;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter

public class PlanViewDTO {
	
	

	    private Integer planId;
	    private String planName;
	    private Integer planDuration;

	    private BigDecimal originalFees;
	    private BigDecimal discountAmount;
	    private BigDecimal finalFees;

	    private String description;

	    private List<String> trainings;
	}


