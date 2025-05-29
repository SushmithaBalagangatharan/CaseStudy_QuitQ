package com.hexaware.quitq.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name="users")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	@Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
	private String userName;
	@Email
	private String email;
	
//	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	private String gender;
	private Long contactNumber;
	private LocalDate createdAt;
	
	@Pattern(regexp="ADMIN|USER|SELLER")
	private String role;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference("user-orders")
	private List<Orders> orderList = new ArrayList<>();
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
//	@JoinColumn(name = "address_id")
	@JsonManagedReference("user-address")
    private Address address;
   
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference("user-rating")
	private List<Rating> rating = new ArrayList<>();
	
	@OneToMany
	@JsonManagedReference("user-payment")
	private List<Payment> payment = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference("user-review")
	private List<Reviews> review = new ArrayList<>();
	
	public UserInfo() {
		
	}
	
	

	

	public UserInfo(Long id,
			String userName,
			 String email,
			String password,
			String gender, Long contactNumber, LocalDate createdAt, String role, List<Orders> orderList, Address address,
			List<Rating> rating, List<Payment> payment, List<Reviews> review) {
		super();
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.createdAt = createdAt;
		this.role = role;
		this.orderList = orderList;
		this.address = address;
		this.rating = rating;
		this.payment = payment;
		this.review = review;
	}





	public List<Payment> getPayment() {
		return payment;
	}



	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
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



	public List<Orders> getOrderList() {
		return orderList;
	}



	public void setOrderList(List<Orders> orderList) {
		this.orderList = orderList;
	}



	public Address getAddress() {
		return address;
	}



	public void setAddress(Address address) {
		this.address = address;
	}



	public List<Rating> getRating() {
		return rating;
	}



	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}



	public List<Reviews> getReview() {
		return review;
	}



	public void setReview(List<Reviews> review) {
		this.review = review;
	}


	

	
	
	
}
