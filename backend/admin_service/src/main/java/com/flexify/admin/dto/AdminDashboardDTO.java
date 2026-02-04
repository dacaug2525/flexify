package com.flexify.admin.dto;

public class AdminDashboardDTO {
	private long totalTrainers;
    private long totalMembers;

    // Constructor
    public AdminDashboardDTO(long totalTrainers, long totalMembers) {
        this.totalTrainers = totalTrainers;
        this.totalMembers = totalMembers;
    }

    // Getters and Setters
    public long getTotalTrainers() { return totalTrainers; }
    public void setTotalTrainers(long totalTrainers) { this.totalTrainers = totalTrainers; }

    public long getTotalMembers() { return totalMembers; }
    public void setTotalMembers(long totalMembers) { this.totalMembers = totalMembers; }
}
