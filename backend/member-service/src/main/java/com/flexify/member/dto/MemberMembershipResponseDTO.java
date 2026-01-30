package com.flexify.member.dto;

import java.math.BigDecimal;
import java.util.List;

public class MemberMembershipResponseDTO {
	

	    private Integer membershipId;
	    private Integer memberId;
	    private String status;

	    // Plan info
	    private String planName;
	    private Integer planDuration;
	    private BigDecimal fees;
	    private String planDescription;

	    // Discount info
	    private Integer discountDuration;
	    private BigDecimal discountAmount;

	    // Training names
	    private List<String> trainingNames;

	    // Getters & Setters
	    public Integer getMembershipId() { return membershipId; }
	    public void setMembershipId(Integer membershipId) { this.membershipId = membershipId; }
	    public Integer getMemberId() { return memberId; }
	    public void setMemberId(Integer memberId) { this.memberId = memberId; }
	    public String getStatus() { return status; }
	    public void setStatus(String status) { this.status = status; }
	    public String getPlanName() { return planName; }
	    public void setPlanName(String planName) { this.planName = planName; }
	    public Integer getPlanDuration() { return planDuration; }
	    public void setPlanDuration(Integer planDuration) { this.planDuration = planDuration; }
	    public BigDecimal getFees() { return fees; }
	    public void setFees(BigDecimal fees) { this.fees = fees; }
	    public String getPlanDescription() { return planDescription; }
	    public void setPlanDescription(String planDescription) { this.planDescription = planDescription; }
	    public Integer getDiscountDuration() { return discountDuration; }
	    public void setDiscountDuration(Integer discountDuration) { this.discountDuration = discountDuration; }
	    public BigDecimal getDiscountAmount() { return discountAmount; }
	    public void setDiscountAmount(BigDecimal discountAmount) { this.discountAmount = discountAmount; }
	    public List<String> getTrainingNames() { return trainingNames; }
	    public void setTrainingNames(List<String> trainingNames) { this.trainingNames = trainingNames; }
	}


