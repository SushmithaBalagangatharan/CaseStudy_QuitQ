package com.hexaware.quitq.entity;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.hexaware.quitq.enums.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Size(min = 2, max = 30, message = "First name must be between 2 and 30 characters")
	private String firstName;
	@Size(min = 2, max = 30, message = "Last name must be between 2 and 30 characters")
	private String lastName;
	@Email
	private String email;
	@Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
	private String password;
	private String gender;
	private Long contactNumber;
	private Date createdAt;
	private Role role;
	
	
	@OneToMany(mappedBy="user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Orders> orderList = new ArrayList<>();
	
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//	private List<Product> productList = new ArrayList<>();

//	@OneToOne(mappedBy = "cart", cascade = CascadeType.ALL)
//	private Cart cart;	
	
	@OneToOne(mappedBy = "user" , cascade = CascadeType.ALL)
	@JsonManagedReference
	@JoinColumn(name = "user_id") 
    private Address address = new Address();
   
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Rating> rating = new ArrayList<>();
	
	@Embedded
	@CollectionTable(name = "payment_information", joinColumns = @JoinColumn(name = "user_id"))
	private List<PaymentInformation> paymentInformation = new ArrayList<>();
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Reviews> review = new ArrayList<>();
	
	public User() {
		
	}

	public User(Long id,
			 String firstName,
			 String lastName,
			String email,
			String password,
			String gender, Long contactNumber, Date createdAt, Role role, List<Orders> orderList, Address address,
			List<Rating> rating, List<PaymentInformation> paymentInformation, List<Reviews> review) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.createdAt = createdAt;
		this.role = role;
		this.orderList = orderList;
		this.address = address;
		this.rating = rating;
		this.paymentInformation = paymentInformation;
		this.review = review;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
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

	public List<PaymentInformation> getPaymentInformation() {
		return paymentInformation;
	}

	public void setPaymentInformation(List<PaymentInformation> paymentInformation) {
		this.paymentInformation = paymentInformation;
	}

	public List<Reviews> getReview() {
		return review;
	}

	public void setReview(List<Reviews> review) {
		this.review = review;
	}

	
	
}
