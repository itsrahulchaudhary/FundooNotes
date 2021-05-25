package com.fundoo.app.user.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	@Id
	@Column(name="userId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@Column(name="name")
	private String name;
	@Column(name="email")
	private String email;
	@Column(name="password")
	private String password;
	@Column(name="mobileNumber")
	private String mobileNumber;
	@Column(name="isVarified")
	boolean isVarified;
	@Column(name="registeredDate")
	private LocalDate registeredDate;
	@Column(name="updatedDate")
	private LocalDate updatedDate;
	
	public User() {
		super();
	}

	public User(long userId, String name, String email, String password, String mobileNumber, boolean isVarified,
			LocalDate registeredDate, LocalDate updatedDate) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.isVarified = isVarified;
		this.registeredDate = registeredDate;
		this.updatedDate = updatedDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean isVarified() {
		return isVarified;
	}

	public void setVarified(boolean isVarified) {
		this.isVarified = isVarified;
	}

	public LocalDate getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDate registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDate getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	

}
