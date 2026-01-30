package com.flexify.member.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

public class MemberRequestDTO {


	    @NotNull(message = "DOB is required")
	    private LocalDateTime dob;

	    @Min(50)
	    @Max(250)
	    private int height;

	    @Min(20)
	    @Max(300)
	    private int weight;

	    @NotBlank
	    @Size(max = 255)
	    private String address;

	    @NotNull(message = "User ID is required")
	    private Integer uid;

	    // getters and setters
	}


