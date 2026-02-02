package com.flexify.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class MemberWithMedicalRequestDTO {
	

	    @NotNull
	    private LocalDate dob;

	    @Min(50) @Max(250)
	    private int height;

	    @Min(20) @Max(300)
	    private int weight;

	    @NotBlank
	    private String address;

	    @NotNull
	    private Integer uid;

	    @Valid
	    private List<MedInfoDTO> medicalInfo;
	}


