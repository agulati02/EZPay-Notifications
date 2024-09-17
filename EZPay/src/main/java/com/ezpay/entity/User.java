package com.ezpay.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.Date;

/**
 * User class Represents a user entity with basic authentication and
 * notification settings.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Entity
@Table(name="users")
public class User {
	@Id
//	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
//    @SequenceGenerator(name = "user_seq", sequenceName = "USER_SEQ", allocationSize = 1)

	@Column(name = "id")
	private String id;  // Unique identifier for the user

	@Column(name = "username")
	private String username;  // Username of the user

	@Column(name = "email")
	private String email;  // Email address of the user
	
	private boolean notificationsEnabled;
	
	private String password;
	
	private Date registrationDate;

	// One-to-many relationship with PaymentReminder. A user can have multiple payment reminders.
    @OneToMany(mappedBy = "user")
    private Set<PaymentReminder> paymentReminders;  // Set of payment reminders associated with this user


	public User() {
		notificationsEnabled = true;
	}

	public String getId() {
        return id;
    }

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

	// Getter and setter methods for paymentReminders
    public Set<PaymentReminder> getPaymentReminders() {
        return paymentReminders;
    }

    public void setPaymentReminders(Set<PaymentReminder> paymentReminders) {
        this.paymentReminders = paymentReminders;
    }
}
