package com.flexify.member.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flexify.member.dto.WorkoutScheduleResponseDTO;
import com.flexify.member.entities.WorkoutSchedule;
import com.flexify.member.repository.WorkoutScheduleRepository;

@Service
public class MemberWorkoutService {
	


	    @Autowired
	    private WorkoutScheduleRepository workoutRepo;

	    // Member views his workout schedule
	    public List<WorkoutScheduleResponseDTO> getWorkoutByMember(Integer mid) {

	        List<WorkoutSchedule> workouts =
	                workoutRepo.findByMember_Mid(mid);

	        return workouts.stream().map(workout -> {

	            WorkoutScheduleResponseDTO dto =
	                    new WorkoutScheduleResponseDTO();

	            dto.setWorkoutId(workout.getWorkoutId());
	            dto.setWorkoutDesc(workout.getWorkoutDesc());
	            dto.setDays(workout.getDays());

	            // trainer name from users table
	            String trainerName =
	                    workout.getTrainer().getUser().getFname()
	                    + " "
	                    + workout.getTrainer().getUser().getLname();

	            dto.setTrainerName(trainerName);

	            return dto;
	        }).toList();
	    }
	}


