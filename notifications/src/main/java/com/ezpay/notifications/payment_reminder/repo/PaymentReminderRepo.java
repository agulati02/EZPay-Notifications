package com.ezpay.notifications.payment_reminder.repo;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ezpay.notifications.payment_reminder.model.PaymentReminder;

/**
 * A dummy data repository for all payment reminders.
 * 
 * Author: Anurag Gulati
 * Date: 11.08.2024
 */
@SuppressWarnings("deprecation")
public class PaymentReminderRepo {

	private ArrayList<PaymentReminder> listOfPaymentReminders;

	{
		this.listOfPaymentReminders = new ArrayList<PaymentReminder>();
		this.listOfPaymentReminders
				.add(new PaymentReminder("R01U01", "U01", 100.0, new Date(2024-1900, 8-1, 12), "Incomplete"));
		this.listOfPaymentReminders
				.add(new PaymentReminder("R02U01", "U01", 150.0, new Date(2024-1900, 8-1, 15), "Incomplete"));
		this.listOfPaymentReminders.add(new PaymentReminder("R11U12", "U12", 50.0, new Date(2024-1900, 8-1, 11), "Complete"));
		this.listOfPaymentReminders
				.add(new PaymentReminder("R05U09", "U09", 250.0, new Date(2024-1900, 10-1, 10), "Incomplete"));
	}
	
	/**
	 * Add a new payment reminder.
	 * 
	 * @param reminderId: (String) Unique key of the payment reminder.
	 * @param userId:     (String) Key to identify the user associated with the
	 *                    reminder.
	 * @param amount:     (Double) Amount due in the payment.
	 * @param dueDate:    (Date) Due date of the payment.
	 * @param status:     (String) Status of the payment reminder.
	 * 
	 * @return (Boolean) A boolean to acknowledge the addition of a payment
	 *         reminder.
	 */
	public boolean addPaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
		PaymentReminder paymentReminder = new PaymentReminder(reminderId, userId, amount, dueDate, status);
		boolean addStatus = this.listOfPaymentReminders.add(paymentReminder);
		System.out.println(this.listOfPaymentReminders.size());
		return addStatus;
	}

	/**
	 * Delete a payment reminder.
	 * 
	 * @param reminderId: (String) Unique to identify the record to be deleted.
	 * 
	 * @return (Boolean) A boolean to acknowledge the deletion of a payment
	 * reminder.
	 */
	
	public boolean deletePaymentReminder(String reminderId) {
	    boolean deletionStatus = false;
	    ArrayList<PaymentReminder> newListOfReminders = new ArrayList<PaymentReminder>();
	    for (PaymentReminder paymentReminder : this.listOfPaymentReminders) {
	        if (paymentReminder.getReminderId().equals(reminderId)) {
	            deletionStatus = true;
	            continue;
	        } else {
	        	newListOfReminders.add(paymentReminder);
	        }
	    }
	    this.listOfPaymentReminders = newListOfReminders;
	    return deletionStatus;
	}


	/**
	 * Delete all payment reminders corresponding to a user.
	 * 
	 * @param userId: (String) Unique key to find and delete all reminders for a
	 * user.
	 * 
	 * @return (Integer) Number of reminders deleted as an acknowledgement.
	 * 
	 * Author : Anurag Gulati
	 * Date: 11.08.2024
	 */
	public Integer deleteAllUserReminders(String userId) {
		Integer numberOfRemindersDeleted = 0;
		ArrayList<PaymentReminder> newListOfReminders = new ArrayList<PaymentReminder>();
		for (PaymentReminder paymentReminder : this.listOfPaymentReminders) {
			if (paymentReminder.getUserId().equals(userId)) {
				numberOfRemindersDeleted += 1;
				continue;
			} else {
				newListOfReminders.add(paymentReminder);
			}
		}
		this.listOfPaymentReminders = newListOfReminders;
		return numberOfRemindersDeleted;
	}

	/**
	 * (Helper) This method is used to generate a reminder message from the
	 * corresponding record
	 * 
	 * @param paymentReminder: (PaymentReminder) A reminder record.
	 * 
	 * @return (String) Reminder text generated from paymentReminder.
	 */
	private String generateMessageFromReminder(PaymentReminder paymentReminder) {
		return "(REM. ID:" + paymentReminder.getReminderId() + ") " + "Payment of Rs. " + String.format("%.2f", paymentReminder.getAmount()) + " due on "
				+ paymentReminder.getDueDate().toString();
	}

	/**
	 * Fetch all payment reminders for a given user.
	 * 
	 * @param userId: (String) Unique key identify the user.
	 * 
	 * @return (ArrayList<String>) List of reminder messages corresponding to the
	 * given user.
	 */
	public ArrayList<String> fetchPaymentReminders(String userId) {
		ArrayList<String> paymentReminders = new ArrayList<String>();
		for (PaymentReminder paymentReminder : this.listOfPaymentReminders) {
			if (paymentReminder.getUserId().equals(userId)) {
				Long differenceMilliseconds = paymentReminder.getDueDate().getTime() - (new Date()).getTime();
				Long differenceDays = TimeUnit.MILLISECONDS.toDays(differenceMilliseconds) + 1;
				if (differenceDays < 3) {
					paymentReminders.add(this.generateMessageFromReminder(paymentReminder));
				}
			}
		}
		return paymentReminders;
	}

}
