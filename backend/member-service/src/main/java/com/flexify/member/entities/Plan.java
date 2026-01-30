package com.flexify.member.entities;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter

@Entity
@Table(name = "plan")
public class Plan {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer planId;

	    @NotNull
	    @Size(min = 2, max = 45)
	    private String planName;

	    @NotNull
	    @Min(1)
	    private Integer planDuration;

	    @NotNull
	    private BigDecimal fees;

	    private String description;

	    @ManyToOne
	    @JoinColumn(name = "dis_id")
	    private PlanDiscount discount;

	    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
	    private List<PlanTraining> planTrainings;

	    // getters & setters
	}


