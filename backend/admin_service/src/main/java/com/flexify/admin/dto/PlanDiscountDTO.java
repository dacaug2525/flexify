package com.flexify.admin.dto;

import java.math.BigDecimal;

public class PlanDiscountDTO {
	private Integer disId;      // <-- add this
    private Integer duration;
    private BigDecimal discount;

    // Getters and Setters
    public Integer getDisId() {
        return disId;
    }

    public void setDisId(Integer disId) {
        this.disId = disId;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

	 
	 
}
