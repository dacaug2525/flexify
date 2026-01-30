package com.flexify.member.entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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
@Table(name = "members")
public class Member {
	


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer mid;

	    @NotNull(message = "Date of birth is required")
	    private LocalDateTime dob;

	    @Min(value = 50, message = "Height must be at least 50 cm")
	    @Max(value = 250, message = "Height cannot exceed 250 cm")
	    private int height;

	    @Min(value = 20, message = "Weight must be at least 20 kg")
	    @Max(value = 300, message = "Weight cannot exceed 300 kg")
	    private int weight;

	    @NotBlank(message = "Address is required")
	    @Size(max = 255)
	    private String address;

	    @NotNull
	    private LocalDateTime joinDate;

	    @Enumerated(EnumType.STRING)
	    @NotNull
	    private Status status;

	    @NotNull(message = "User ID (uid) is mandatory")
	    private Integer uid; // FK from users table

	    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<MedInfo> medicalInfo = new ArrayList<>();

	    public enum Status {
	        active, inactive
	    }

	    // getters and setters
	}



