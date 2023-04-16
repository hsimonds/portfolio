package com.portfolio.model;

import java.util.UUID;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table (name="portfolio_users")
public class User {
	
	//Generate a random ID to use as a displayName for the users profile until they update it
	@Transient
	private String random = UUID.randomUUID().toString();
			
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "Username cannot be empty")
	@Column(name = "user_name", unique=true, nullable=false)
	private String username;
	
	@Column(name = "first_name")
	private String fname;
	
	@Column(name = "last_name")
	private String lname;
	
	@Column(name = "email", unique=true, nullable=false)
	private String email;
	
	@NotEmpty(message = "Password cannot be empty")
	@Column(name = "password", nullable=false)
	private String password;
	
	private String roles;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="profile_id")
	private Profile profile = new Profile(random, "","","","");
	
	public User() {}
	
	public User(String username, String fname, String lname, String email, String password, String roles) {
		this.username = username;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Profile getProfile() {
		return profile;
	}

	public void setProfile(Profile profile) {
		this.profile = profile;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}