package com.flexify.admin.dto;

import java.math.BigDecimal;

public class TrainerListDTO {
	private Integer uid;
    private String uname;
    private String fname;
    private String lname;
    private String contact;
    private Integer experience;
    public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public Integer getExperience() {
		return experience;
	}
	public void setExperience(Integer experience) {
		this.experience = experience;
	}
	public void setSalary(BigDecimal salary) {
		// TODO Auto-generated method stub
		
	}
	public void setEmail(String email) {
		// TODO Auto-generated method stub
		
	}

}
