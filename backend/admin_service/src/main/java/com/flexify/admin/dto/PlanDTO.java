package com.flexify.admin.dto;

import java.math.BigDecimal;

public class PlanDTO {
	 	private String planName;
	    private Integer planDuration;
	    private BigDecimal fees;
	    private String description;
	    private Integer disId;
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
	    
	    
}
