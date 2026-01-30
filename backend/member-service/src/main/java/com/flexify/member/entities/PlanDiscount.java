package com.flexify.member.entities;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "plan_discount")
public class PlanDiscount {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Integer disId;

	    @NotNull
	    @Min(1)
	    private Integer duration; // in months

	    @NotNull
	    private BigDecimal discount;

	    // getters & setters
	    public Integer getDisId() { return disId; }
	    public void setDisId(Integer disId) { this.disId = disId; }
	    public Integer getDuration() { return duration; }
	    public void setDuration(Integer duration) { this.duration = duration; }
	    public BigDecimal getDiscount() { return discount; }
	    public void setDiscount(BigDecimal discount) { this.discount = discount; }
	}


