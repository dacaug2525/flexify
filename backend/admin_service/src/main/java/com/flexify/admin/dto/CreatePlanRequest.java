package com.flexify.admin.dto;

import java.math.BigDecimal;
import java.util.List;

public class CreatePlanRequest {
	
	private String planName;
    private Integer planDuration;
    private BigDecimal fees;
    private String description;
    private Integer disId;
    private List<Integer> trainerIds;
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
	public Integer getDisId() {
		return disId;
	}
	public void setDisId(Integer disId) {
		this.disId = disId;
	}
	public List<Integer> getTrainerIds() {
		return trainerIds;
	}
	public void setTrainerIds(List<Integer> trainerIds) {
		this.trainerIds = trainerIds;
	}
    
    
}
