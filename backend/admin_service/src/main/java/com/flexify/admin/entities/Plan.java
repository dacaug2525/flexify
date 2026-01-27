package com.flexify.admin.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "plan")
public class Plan {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer planId;

    @Column(nullable = false)
    private String planName;

    @Column(nullable = false)
    private Integer planDuration;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal fees;

    private String description;

    //many plan having one discount(PlanDiscount)
    @ManyToOne
    @JoinColumn(name = "dis_id", nullable = false)
    private PlanDiscount discount;   
    
	public Plan() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Plan(Integer planId, String planName, Integer planDuration, BigDecimal fees, String description,
			PlanDiscount discount) {
		super();
		this.planId = planId;
		this.planName = planName;
		this.planDuration = planDuration;
		this.fees = fees;
		this.description = description;
		this.discount = discount;
	}

	public Integer getPlanId() {
		return planId;
	}

	public void setPlanId(Integer planId) {
		this.planId = planId;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanDuration() {
		return planDuration;
	}

	public void setPlanDuration(Integer planDuration) {
		this.planDuration = planDuration;
	}

	public BigDecimal getFees() {
		return fees;
	}

	public void setFees(BigDecimal fees) {
		this.fees = fees;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PlanDiscount getDiscount() {
		return discount;
	}

	public void setDiscount(PlanDiscount discount) {
		this.discount = discount;
	}
    
    
}
