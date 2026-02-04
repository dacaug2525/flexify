package com.flexify.admin.dto;

import java.time.LocalDateTime;

public class AssignmentDTO {
	private Integer assignmentId;
    private Integer mid;
    private Integer tid;
    private String memberName;
    private String trainerName;
    private LocalDateTime assignDate;
    
    public AssignmentDTO(Integer assignmentId, Integer mid, Integer tid,
            String memberName, String trainerName, LocalDateTime assignDate) {
this.assignmentId = assignmentId;
this.mid = mid;
this.tid = tid;
this.memberName = memberName;
this.trainerName = trainerName;
this.assignDate = assignDate;
}
    
    
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
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getTrainerName() {
		return trainerName;
	}
	public void setTrainerName(String trainerName) {
		this.trainerName = trainerName;
	}
	public LocalDateTime getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(LocalDateTime assignDate) {
		this.assignDate = assignDate;
	}
    
    

}
