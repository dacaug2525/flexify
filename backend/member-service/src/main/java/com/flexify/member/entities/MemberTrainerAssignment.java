package com.flexify.member.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "member_trainer_assignment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberTrainerAssignment {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "assignment_id")
	    private Integer assignmentId;

	    /* FK → trainers.tid */
	    @ManyToOne
	    @JoinColumn(name = "tid", nullable = false)
	    private Trainer trainer;

	    /* FK → members.mid */
	    @ManyToOne
	    @JoinColumn(name = "mid", nullable = false)
	    private Member member;

	    @Column(name = "assign_date", nullable = false)
	    private LocalDateTime assignDate;
	}


