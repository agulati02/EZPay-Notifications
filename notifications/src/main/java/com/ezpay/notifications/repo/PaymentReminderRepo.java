package com.ezpay.notifications.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.ezpay.notifications.model.PaymentReminder;

/**
 * A repository for payment reminders using JDBC.
 * 
 * Sprint: 2 Author: Doneela Das Date: 20.08.2024
 */

//@SuppressWarnings("deprecation")
public class PaymentReminderRepo {
	private DBConnectionPaymentReminder dbConnection;

	public PaymentReminderRepo() {
		this.dbConnection = new DBConnectionPaymentReminder();
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
		return dbConnection.addPaymentReminder(reminderId, userId, amount, new java.sql.Date(dueDate.getTime()),
				status);
	}

	/**
	 * Delete a payment reminder.
	 * 
	 * @param reminderId: (String) Unique to identify the record to be deleted.
	 * 
	 * @return (Boolean) A boolean to acknowledge the deletion of a payment
	 *         reminder.
	 */

	public boolean deletePaymentReminder(String reminderId) {
		return dbConnection.deletePaymentReminder(reminderId);
	}

	/**
	 * Delete all payment reminders corresponding to a user.
	 * 
	 * @param userId: (String) Unique key to find and delete all reminders for a
	 *                user.
	 * 
	 * @return (Integer) Number of reminders deleted as an acknowledgement.
	 *
	 */

	public Integer deleteAllUserReminders(String userId) {
		return dbConnection.deleteAllUserReminders(userId);
	}

	/**
	 * Fetch all payment reminders for a given user with due date in the past or
	 * within the next 3 days.
	 * 
	 * @param userId: (String) Unique key identify the user.
	 * 
	 * @return (ArrayList<String>) List of reminder messages corresponding to the
	 *         given user.
	 */
	public ArrayList<PaymentReminder> fetchPaymentReminders(String userId) {
		ArrayList<PaymentReminder> paymentReminders = new ArrayList<>();
		try (Connection connection = dbConnection.getConnection()) {
			ResultSet resultSet = dbConnection.fetchPaymentReminders(userId);

			if (resultSet != null) {
				while (resultSet.next()) {
					String reminderId = resultSet.getString("reminderId");
					String user = resultSet.getString("userId");
					Double amount = resultSet.getDouble("amount");
					Date dueDate = resultSet.getDate("dueDate");
					String status = resultSet.getString("status");

					PaymentReminder paymentReminder = new PaymentReminder(reminderId, user, amount, dueDate, status);
					paymentReminders.add(paymentReminder);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return paymentReminders;
	}

}
