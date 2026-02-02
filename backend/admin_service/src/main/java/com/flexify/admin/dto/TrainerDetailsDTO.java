package com.flexify.admin.dto;

import java.math.BigDecimal;
import java.util.List;

public class TrainerDetailsDTO {
	private Integer uid;
	private Integer tid;
    private String uname;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String gender;

    private Integer experience;
    private BigDecimal salary;

    private List<TrainerSpecializationDTO> specializations;

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

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

	public List<TrainerSpecializationDTO> getSpecializations() {
		return specializations;
	}

	public void setSpecializations(List<TrainerSpecializationDTO> specializations) {
		this.specializations = specializations;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

  
    
}
