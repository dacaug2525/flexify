package com.flexify.member.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "plan_training")
public class PlanTraining {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "pt_id")
	    private Integer ptId;

	    @ManyToOne
	    @JoinColumn(name = "plan_id", nullable = false)
	    private Plan plan;

	    @ManyToOne
	    @JoinColumn(name = "tr_id", nullable = false)
	    private TrainingTable training;

	    // getters & setters
	
	}


