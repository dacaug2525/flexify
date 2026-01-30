package com.flexify.member.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedInfoDTO {
	
	    @NotNull(message = "Health condition ID is required")
	    private Integer healthId;

	    @Size(max = 255)
	    private String remark;

	    // getters and setters
	}


