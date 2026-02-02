package com.flexify.admin.dto;

import java.time.LocalDateTime;

public class AssignTrainerResponseDTO {
	private Integer assignmentId;
    private Integer tid;
    private Integer mid;
    private LocalDateTime assignDate;
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
