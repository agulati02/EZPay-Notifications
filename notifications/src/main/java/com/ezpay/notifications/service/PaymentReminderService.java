package com.ezpay.notifications.service;

import java.util.ArrayList;
import java.util.Date;

import com.ezpay.notifications.repo.PaymentReminderRepo;

/**
 * This is the service layer for payment reminders.
 * Author: Anurag Gulati
 * Date: 11.08.2024
 */
public class PaymentReminderService {

	private PaymentReminderRepo paymentReminderRepo;

	public PaymentReminderService() {}

	/**
	 * Parameterized constructor in case repository is obtained from external source.
	 * 
	 * @param	repo: (PaymentReminderRepo) Repository obtained externally.
	 */
	public PaymentReminderService(PaymentReminderRepo repo) {
		this.paymentReminderRepo = repo;
	}

	/**
	 * Service to add a new payment reminder.
	 * 
	 * @param	reminderId: (String) Unique reminder ID.
	 * @param	userId: (String) Unique user identification code.
	 * @param	amount: (Double) Amount due in the payment.
	 * @param	dueDate: (Date) Due date of the payment.
	 * @param	status: (String) Reminder status.
	 * 
	 * @return	(Boolean) Acknowledging the addition of payment reminder.
	 */
	public boolean addPaymentReminderService(String reminderId, String userId, Double amount, Date dueDate,
			String status) {
		if (reminderId != null && userId != null) {
			return this.paymentReminderRepo.addPaymentReminder(reminderId, userId, amount, dueDate, status);
		} else {
			return false;
		}
	}

	/**
	 * This method is used to delete a reminder record based on a unique key.
	 * 
	 * @param reminderId: (String) Unique to identify the record to be deleted.
	 * 
	 * @return (Boolean) A boolean to acknowledge the deletion of a payment
	 * reminder.
	 */
	public boolean deletePaymentReminderService(String reminderId) {
		if (reminderId != null) {
			return this.paymentReminderRepo.deletePaymentReminder(reminderId);
		} else {
			return false;
		}
	}
	
	/**
	 * This method is used to delete all payment reminders corresponding to a user.
	 * 
	 * @param userId: (String) Unique key to find and delete all reminders for a
	 * user.
	 * 
	 * @return (Integer) Number of reminders deleted as an acknowledgement.
	 */
	public Integer deleteAllUserRemindersService(String userId) {
		if (userId != null) {
			return this.paymentReminderRepo.deleteAllUserReminders(userId);
		} else {
			return 0;
		}
	}

	/**
	 * This method is used to fetch all payment reminders for a given user.
	 * 
	 * @param userId: (String) Unique key identify the user.
	 * 
	 * @return (ArrayList<String>) List of reminder messages corresponding to the
	 * given user.
	 */
	public ArrayList<String> fetchPaymentRemindersService(String userId) {
		if (userId != null) {
			return this.paymentReminderRepo.fetchPaymentReminders(userId);
		} else {
			return new ArrayList<String>();
		}
	}
	
}
