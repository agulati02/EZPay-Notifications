package com.ezpay.notifications.model;

import java.util.Date;

/**
 * This module outlines the attributes and core 
 * functionalities of a payment reminder.
 * 
 * Author: Anurag Gulati
 * Date: 10.08.2024
 */
public class PaymentReminder {

	private String reminderId;
	private String userId;
	private Double amount;
	private Date dueDate;
	private String status;

	/**
	 * Default constructor.
	 */
	public PaymentReminder() {
		super();
		this.reminderId = null;
		this.userId = null;
		this.amount = 0.0;
		this.dueDate = null;
		this.status = null;
	}

	/**
	 * @param reminderId	Primary key for a reminder.
	 * @param userId		User ID associated with a reminder.
	 * @param amount		Amount due in the payment.
	 * @param dueDate		Due date of the payment.
	 * @param status		Reminder status.
	 */
	public PaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
		super();
		this.reminderId = reminderId;
		this.userId = userId;
		this.amount = amount;
		this.dueDate = dueDate;
		this.status = status;
	}

	/**
	 * @return the reminderId
	 */
	public String getReminderId() {
		return reminderId;
	}

	/**
	 * @param reminderId the reminderId to set
	 */
	public void setReminderId(String reminderId) {
		this.reminderId = reminderId;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}

