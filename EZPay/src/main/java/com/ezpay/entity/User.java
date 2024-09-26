package com.ezpay.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * User class Represents a user entity with basic authentication and
 * notification settings.
 * No external module used
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Entity
@Table(name="users")
public class User {
	@Id
	private String id;
	private boolean notificationsEnabled;
	private String username;
	private String password;
	private String email;
	private Date registrationDate;

	public User() {
		notificationsEnabled = true;
	}

	//Getters and Setters
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isNotificationsEnabled() {
		return notificationsEnabled;
	}

	public void setNotificationsEnabled(boolean notificationsEnabled) {
		this.notificationsEnabled = notificationsEnabled;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

}
