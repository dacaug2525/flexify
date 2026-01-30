package com.flexify.member.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "med_info")
public class MedInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer medId;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "mid", nullable = false)
	@NotNull
	private Member member;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "health_id", nullable = false)
	@NotNull
	private HealthCondition healthCondition;

	@Size(max = 255)
	private String remark;

	// getters and setters
}
