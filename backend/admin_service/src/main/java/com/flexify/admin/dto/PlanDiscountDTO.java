package com.flexify.admin.dto;

import java.math.BigDecimal;

public class PlanDiscountDTO {
	 private Integer duration;
	 private BigDecimal discount;
	 
	 
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
