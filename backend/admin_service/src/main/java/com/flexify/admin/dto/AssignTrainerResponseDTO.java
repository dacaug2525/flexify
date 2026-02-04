package com.flexify.admin.dto;

import java.time.LocalDateTime;

public class AssignTrainerResponseDTO {
	
	  private Integer assignmentId;
	  private Integer mid;
	  private Integer tid;
	  private Integer trainingId;
	  private LocalDateTime assignDate;
	  public Integer getAssignmentId() {
		  return assignmentId;
	  }
	  public void setAssignmentId(Integer assignmentId) {
		  this.assignmentId = assignmentId;
	  }
	  public Integer getMid() {
		  return mid;
	  }
	  public void setMid(Integer mid) {
		  this.mid = mid;
	  }
	  public Integer getTid() {
		  return tid;
	  }
	  public void setTid(Integer tid) {
		  this.tid = tid;
	  }
	  public Integer getTrainingId() {
		  return trainingId;
	  }
	  public void setTrainingId(Integer trainingId) {
		  this.trainingId = trainingId;
	  }
	  public LocalDateTime getAssignDate() {
		  return assignDate;
	  }
	  public void setAssignDate(LocalDateTime assignDate) {
		  this.assignDate = assignDate;
	  }
	
	  
}
