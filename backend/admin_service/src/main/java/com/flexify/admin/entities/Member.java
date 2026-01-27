package com.flexify.admin.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "members")
public class Member {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "mid")
	    private Integer mid;

	    @Column(name = "dob", nullable = false)
	    private LocalDateTime dob;

	    @Column(name = "height", nullable = false)
	    private Integer height;

	    @Column(name = "weight", nullable = false)
	    private Integer weight;

	    @Column(name = "address", length = 255, nullable = false)
	    private String address;

	    @Column(name = "join_date", nullable = false)
	    private LocalDateTime joinDate;

	    @Enumerated(EnumType.STRING)
	    @Column(name = "status")
	    private Status status;

	    @Column(name = "uid", nullable = false)
	    private Integer uid;    // Foreign key from user table

		public Member() {
			super();
			// TODO Auto-generated constructor stub
		}

		public Member(Integer mid, LocalDateTime dob, Integer height, Integer weight, String address,
				LocalDateTime joinDate, Status status, Integer uid) {
			super();
			this.mid = mid;
			this.dob = dob;
			this.height = height;
			this.weight = weight;
			this.address = address;
			this.joinDate = joinDate;
			this.status = status;
			this.uid = uid;
		}

		public Integer getMid() {
			return mid;
		}

		public void setMid(Integer mid) {
			this.mid = mid;
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
