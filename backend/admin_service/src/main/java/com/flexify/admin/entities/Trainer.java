package com.flexify.admin.entities;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "trainers")
public class Trainer {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    private Integer tid;

    @Column(name = "experience")
    private Integer experience;   // years of experience

    @Column(name = "salary", precision = 10, scale = 2)
    private BigDecimal salary;    // DECIMAL(10,2)

    @Column(name = "uid", nullable = false)
    private Integer uid;          // FK from user table (auth service)

	public Trainer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Trainer(Integer tid, Integer experience, BigDecimal salary, Integer uid) {
		super();
		this.tid = tid;
		this.experience = experience;
		this.salary = salary;
		this.uid = uid;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
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

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

    
}
