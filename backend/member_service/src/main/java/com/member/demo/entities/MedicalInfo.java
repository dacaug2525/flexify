package com.member.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
@JsonIgnoreProperties({"member"})
public class MedicalInfo {
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    int medId;
	 
	    @JsonIgnore 
	     @ManyToOne
	     @JoinColumn(name = "mid")
	     Member member;

	    @ManyToOne
	    @JoinColumn(name = "health_id")
	     HealthCondition healthCondition;
	    
	    String remark;
}
