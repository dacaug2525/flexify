package com.flexify.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutScheduleResponseDTO {

	private Integer workoutId;
	private String workoutDesc;
	private int days;
	private String trainerName;
}
