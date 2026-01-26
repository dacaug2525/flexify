package com.member.demo.entities;

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

@Entity
@Table(name="med_info")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class MedicalInfo {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	     int medId;

	     @ManyToOne
	     @JoinColumn(name = "mid")
	     Member member;

	    @ManyToOne
	    @JoinColumn(name = "health_id")
	     HealthCondition healthCondition;
}
