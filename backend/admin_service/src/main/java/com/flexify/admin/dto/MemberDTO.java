package com.flexify.admin.dto;

import java.time.LocalDateTime;

import com.flexify.admin.entities.Status;

public class MemberDTO {
	
	 private LocalDateTime dob;
	 private Integer height;
	 private Integer weight;
	 private String address;
	 private LocalDateTime joinDate;
	 private Status status;
	 private Integer uid;
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
	 public LocalDateTime getJoinDate() {
		 return joinDate;
	 }
	 public void setJoinDate(LocalDateTime joinDate) {
		 this.joinDate = joinDate;
	 }
	 public Status getStatus() {
		 return status;
	 }
	 public void setStatus(Status status) {
		 this.status = status;
	 }
	 public Integer getUid() {
		 return uid;
	 }
	 public void setUid(Integer uid) {
		 this.uid = uid;
	 }
	 
	 
}
