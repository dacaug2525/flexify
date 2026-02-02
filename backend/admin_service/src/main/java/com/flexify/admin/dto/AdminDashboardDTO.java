package com.flexify.admin.dto;

public class AdminDashboardDTO {
	private Integer totalTrainers;
    private Integer totalMembers;
    
    public AdminDashboardDTO(Integer totalTrainers, Integer totalMembers) {
        this.totalTrainers = totalTrainers;
        this.totalMembers = totalMembers;  
     }

	public Integer getTotalTrainers() {
		return totalTrainers;
	}

	public Integer getTotalMembers() {
		return totalMembers;
	}

}
