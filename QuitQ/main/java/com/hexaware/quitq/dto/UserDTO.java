package com.hexaware.quitq.dto;

import java.time.LocalDate;

public class UserDTO {
	private String userName;
	private String email;
	private String password;
    private String gender;
    private Long contactNumber;
    private LocalDate createdAt;
    private String role;
	
	public UserDTO() {
		
	}
	
	

	public UserDTO(String userName, String email, String password, String gender, Long contactNumber,
			LocalDate createdAt, String role) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.createdAt = createdAt;
		this.role = role;
	}



	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	

	



	
}
