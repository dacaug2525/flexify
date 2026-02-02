package com.flexify.member.dto;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TrainerAssignmentResponseDTO {
	

	    private Integer trainerId;
	    private String trainerName;
	    private Integer experience;
	    private String email;
	    private String contact;

	    private LocalDateTime assignedDate;
	}


