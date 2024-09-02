package com.example.Notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.aspectj.weaver.ast.Not;

/**
 * Notification class
 * Represents a notification entity linked to a user and transaction.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long transactionId;
    private String notificationContent;

    /**
     * Constructor to initialize Notification with details.
     * 
     * @param notificationContent Content of the notification.
     * @param userId              User ID associated with the notification.
     * @param transactionId       Transaction ID linked to the notification.
     */

    public Notification(String notificationContent, Long userId, Long transactionId) {
        this.notificationContent = notificationContent;
        this.userId = userId;
        this.transactionId = transactionId;
    }

    public Notification() {
    }

    // Getters and setters

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Long getTransactionId() {
        return transactionId;
    }
}
