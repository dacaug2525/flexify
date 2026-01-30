package com.flexify.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class PlanDTO {
	
	    @NotNull
	    @Size(min = 2, max = 45)
	    private String planName;

	    @NotNull
	    private Integer planDuration;

	    @NotNull
	    private BigDecimal fees;

	    private String description;

	    @NotNull
	    private Integer discountId; // FK to plan_discount

	    // getters & setters
	}


