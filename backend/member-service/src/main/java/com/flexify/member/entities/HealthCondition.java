package com.flexify.member.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "health_condition")
public class HealthCondition {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer healthId;

	@NotBlank(message = "Health condition name is required")
	@Size(max = 45)
	private String name;

	// getters and setters
}
