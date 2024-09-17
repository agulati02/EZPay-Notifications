package com.ezpay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

/**
 * Notification class
 * Represents a notification entity linked to a user and transaction.
 * No external module used
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq")
    @SequenceGenerator(name = "notification_seq", sequenceName = "NOTIFICATION_SEQ", allocationSize = 1)
    private Long id;
	@SuppressWarnings("unused")
    private String userId;
    private Long transactionId;
    private String notificationContent;

    /**
     * Constructor to initialize Notification with details.
     * 
     * @param notificationContent Content of the notification.
     * @param userId              User ID associated with the notification.
     * @param transactionId       Transaction ID linked to the notification.
     */

    public Notification(String notificationContent, String userId, Long transactionId) {
        this.notificationContent = notificationContent;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Notification() {
    }

    // Getters and setters
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}

	public String getNotificationContent() {
		return notificationContent;
	}

	public void setNotificationContent(String notificationContent) {
		this.notificationContent = notificationContent;
	}

    
}
