package com.member.demo.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MemberUpdateDTO {

	
    @JsonFormat(pattern = "yyyy-MM-dd")
	LocalDate dob;
	int height;
	int weight;
	String address;

	String status;

	List<MedInfoDTO> healthConditions;
}
