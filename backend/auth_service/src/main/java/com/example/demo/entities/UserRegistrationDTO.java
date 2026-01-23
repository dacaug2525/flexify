package com.example.demo.entities;

public class UserRegistrationDTO {
	private String uname;
    private String password;
    private String fname;
    private String lname;
    private String email;
    private String contact;
    private String gender;
    private int roleid;
    
    
	public UserRegistrationDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserRegistrationDTO(String uname, String password, String fname, String lname, String email, String contact,
			String gender, int roleid) {
		super();
		this.uname = uname;
		this.password = password;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.contact = contact;
		this.gender = gender;
		this.roleid = roleid;
	}
	
	
	
	
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	
    
}
