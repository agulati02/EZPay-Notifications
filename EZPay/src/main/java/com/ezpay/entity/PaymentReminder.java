package com.ezpay.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * This module outlines the attributes and core functionalities of a payment
 * reminder.
 * 
 * Author: Doneela Das
 * Date: 02-09-2024
 */
@Entity
@Table(name = "payment_reminder")
public class PaymentReminder {

    /**
     * Primary key for a payment reminder.
     */
    @Id
    @Column(name = "reminder_id")
    private String reminderId;

    /**
     * The ID of the user associated with the payment reminder.
     */
    @Column(name = "user_id")
    private String userId;

    /**
     * The amount due for the payment.
     */
    @Column(name = "amount")
    private Double amount;

    /**
     * The due date for the payment.
     */
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    /**
     * The status of the payment reminder (e.g., 'Pending', 'Paid').
     */
    @Column(name = "status")
    private String status;

    /**
     * The email address of the user for sending reminders.
     */
    @Column(name = "user_email")  // Add the user email field
    private String userEmail;

    /**
     * Default constructor.
     */
    public PaymentReminder() {
        super();
    }

    /**
     * Parameterized constructor.
     * 
     * @param reminderId Primary key for a reminder.
     * @param userId     User ID associated with a reminder.
     * @param amount     Amount due in the payment.
     * @param dueDate    Due date of the payment.
     * @param status     Reminder status.
     * @param userEmail  Email address of the user.
     */
    public PaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status, String userEmail) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
        this.userEmail = userEmail;
    }

    // Getters and setters

    /**
     * Gets the reminder ID.
     * 
     * @return the reminder ID.
     */
    public String getReminderId() {
        return reminderId;
    }

    /**
     * Sets the reminder ID.
     * 
     * @param reminderId the reminder ID to set.
     */
    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    /**
     * Gets the user ID associated with the reminder.
     * 
     * @return the user ID.
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the user ID associated with the reminder.
     * 
     * @param userId the user ID to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the amount due for the payment.
     * 
     * @return the amount due.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Sets the amount due for the payment.
     * 
     * @param amount the amount to set.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

    /**
     * Gets the due date for the payment.
     * 
     * @return the due date.
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Sets the due date for the payment.
     * 
     * @param dueDate the due date to set.
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Gets the status of the payment reminder.
     * 
     * @return the status of the reminder.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the payment reminder.
     * 
     * @param status the status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets the user email associated with the reminder.
     * 
     * @return the user email.
     */
    public String getUserEmail() {
        return userEmail;
    }

    /**
     * Sets the user email associated with the reminder.
     * 
     * @param userEmail the email to set.
     */
    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    /**
     * Returns a string representation of the payment reminder.
     * 
     * @return string representation of the reminder.
     */
    @Override
    public String toString() {
        return "[REM. ID:" + this.reminderId + "] Payment of Rs. " + String.format("%.2f", this.amount) + " due on "
                + this.dueDate.toString();
    }
}
