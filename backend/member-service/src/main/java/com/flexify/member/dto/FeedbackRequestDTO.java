package com.flexify.member.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackRequestDTO {
	

	    @NotNull
	    private Integer mid;

	    @NotNull
	    private Integer tid;

	    @NotBlank
	    private String comment;

	    @Min(1)
	    @Max(5)
	    private int rating;
	}


