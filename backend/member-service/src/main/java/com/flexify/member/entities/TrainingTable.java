package com.flexify.member.entities;
import jakarta.persistence.*;
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
@Table(name = "training_table")
public class TrainingTable {


	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer trId;

	    @NotNull
	    @Size(min = 2, max = 45)
	    private String trName;

	    @NotNull
	    private String desc;

	    // getters & setters
	}


