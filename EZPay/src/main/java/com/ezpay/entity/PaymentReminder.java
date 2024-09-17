package com.ezpay.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * 
 * Author: Doneela Das
 * Date: 02-13-2024
 */

@Entity
@Table(name = "payment_reminder")
public class PaymentReminder {

    @Id
    @Column(name = "reminder_id")
    private String reminderId;  // Unique identifier for the payment reminder

    @Column(name = "user_id")
    private String userId;  // The ID of the user associated with this payment reminder

    @Column(name = "amount")
    private Double amount;  // The amount that is due for this payment reminder

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;  // The due date for the payment

    @Column(name = "status")
    private String status;  // The status of the reminder (e.g., pending, paid)

    // Mapping userEmail with a ManyToOne relationship to link the user who owns this reminder
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;  // Reference to the User entity. Assuming User entity has an email field.

    // Default constructor
    public PaymentReminder() {
        super();
    }

    // Parameterized constructor
    public PaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getter and setter methods for reminderId
    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    // Getter and setter methods for userId
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    // Getter and setter methods for amount
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    // Getter and setter methods for dueDate
    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    // Getter and setter methods for status
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    // Method to retrieve the email of the associated user, if available
    public String getUserEmail() {
        return user != null ? user.getEmail() : null;
    }

    // toString method for displaying the reminder details
    @Override
    public String toString() {
        return "[REM. ID:" + this.reminderId + "] Payment of Rs. " + String.format("%.2f", this.amount) + " due on "
                + this.dueDate.toString();
    }
}
