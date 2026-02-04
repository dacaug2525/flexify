package com.flexify.admin.dto;

import java.time.LocalDateTime;

public class MemberDetailDTO {
	private Integer mid;
    private String email;
    private LocalDateTime dob;
    private Integer height;
    private Integer weight;
    private String address;
    private String gender;
    
    public MemberDetailDTO(
            Integer mid,
            String email,
            LocalDateTime dob,
            Integer height,
            Integer weight,
            String address,
            String gender
    ) {
        this.mid = mid;
        this.email = email;
        this.dob = dob;
        this.height = height;
        this.weight = weight;
        this.address = address;
        this.gender = gender;
    }
    
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDateTime getDob() {
		return dob;
	}
	public void setDob(LocalDateTime dob) {
		this.dob = dob;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
    
    
}
