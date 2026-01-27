package com.flexify.admin.dto;

import java.math.BigDecimal;

public class TrainerDTO {
	private Integer experience;
    private BigDecimal salary;
    private Integer uid;
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
    
    
}
