package com.flexify.admin.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "member_trainer_assignment")
public class MemberTrainerAssignment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignment_id")
    private Integer assignmentId;

    @Column(name = "tid", nullable = false)
    private Integer tid;

    @Column(name = "mid", nullable = false)
    private Integer mid;

    @Column(name = "assign_date", nullable = false)
    private LocalDateTime assignDate;

	public MemberTrainerAssignment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MemberTrainerAssignment(Integer assignmentId, Integer tid, Integer mid, LocalDateTime assignDate) {
		super();
		this.assignmentId = assignmentId;
		this.tid = tid;
		this.mid = mid;
		this.assignDate = assignDate;
	}

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public LocalDateTime getAssignDate() {
		return assignDate;
	}

	public void setAssignDate(LocalDateTime assignDate) {
		this.assignDate = assignDate;
	}
    
    
}
