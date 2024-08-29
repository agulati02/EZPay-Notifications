package com.example.Notification.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.aspectj.weaver.ast.Not;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long transactionId;
    private String notificationString;

    public Notification(String notificationString, Long userId, Long tid) {
        setNotificationString(notificationString);
        this.userId = userId;
        this.transactionId = tid;
    }
    public Notification(){}

    public String getNotificationString() {
        return notificationString;
    }

    public void setNotificationString(String notificationString) {
        this.notificationString = notificationString;
    }

    public Long getTransactionId() {
        return transactionId;
    }
}
