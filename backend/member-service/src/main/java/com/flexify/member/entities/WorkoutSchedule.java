package com.flexify.member.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "workout_schedule")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutSchedule {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer workoutId;

	    @ManyToOne
	    @JoinColumn(name = "trainer_id", nullable = false)
	    private Trainer trainer;

	    @ManyToOne
	    @JoinColumn(name = "member_id", nullable = false)
	    private Member member;

	    @Column(length = 255)
	    private String workoutDesc;

	    private int days;
	}


