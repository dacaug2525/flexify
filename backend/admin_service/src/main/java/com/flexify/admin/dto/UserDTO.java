package com.flexify.admin.dto;

public class UserDTO {
	private long uid;
    private String name;
    private String email;
    private String role;

    // Constructor
    public UserDTO(long uid, String name, String email, String role) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // Getters and Setters
    public long getUid() { return uid; }
    public void setUid(long uid) { this.uid = uid; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
