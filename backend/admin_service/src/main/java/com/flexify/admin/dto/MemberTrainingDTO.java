package com.flexify.admin.dto;

public class MemberTrainingDTO {
	 private String memberName;
	    private int trainingId;
	    private String trainerName;
	    private String specializationDescription;
	    
	    
	    public MemberTrainingDTO(String memberName, String trainerName, String specializationDescription, int trainingId) {
	        this.memberName = memberName;
	        this.trainerName = trainerName;
	        this.specializationDescription = specializationDescription;
	        this.trainingId = trainingId;
	    }

		public String getMemberName() {
			return memberName;
		}
		public void setMemberName(String memberName) {
			this.memberName = memberName;
		}
		public int getTrainingId() {
			return trainingId;
		}
		public void setTrainingId(int trainingId) {
			this.trainingId = trainingId;
		}
		public String getTrainerName() {
			return trainerName;
		}
		public void setTrainerName(String trainerName) {
			this.trainerName = trainerName;
		}
		public String getSpecializationDescription() {
			return specializationDescription;
		}
		public void setSpecializationDescription(String specializationDescription) {
			this.specializationDescription = specializationDescription;
		}
	    
	     
}
