package main.java.com.example.Notification.model;

import javax.persistence.*;
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

    @Id
    @Column(name = "reminder_id")
    private String reminderId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;

    @Column(name = "status")
    private String status;

    /**
     * Default constructor.
     */
    public PaymentReminder() {
        super();
    }

    /**
     * @param reminderId Primary key for a reminder.
     * @param userId     User ID associated with a reminder.
     * @param amount     Amount due in the payment.
     * @param dueDate    Due date of the payment.
     * @param status     Reminder status.
     */
    public PaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
        this.reminderId = reminderId;
        this.userId = userId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.status = status;
    }

    // Getters and Setters

    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "[REM. ID:" + this.reminderId + "] Payment of Rs. " + String.format("%.2f", this.amount) + " due on "
                + this.dueDate.toString();
    }
}
