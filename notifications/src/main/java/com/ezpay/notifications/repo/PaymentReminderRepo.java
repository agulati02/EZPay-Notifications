package com.ezpay.notifications.repo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import com.ezpay.notifications.model.PaymentReminder;

/**
 * A repository for payment reminders using JDBC.
 * <p>
 * Sprint: 2
 * Author: Doneela Das
 * Date: 20.08.2024
 */
public class PaymentReminderRepo {
    private DBConnectionPaymentReminder dbConnection;

    /**
     * Initializes the repository and establishes a database connection.
     */
    public PaymentReminderRepo() {
        try {
            this.dbConnection = new DBConnectionPaymentReminder();
        } catch (ClassNotFoundException e) {
            System.err.println("Database driver class not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database connection error.");
            e.printStackTrace();
        }
    }

    /**
     * Add a new payment reminder.
     *
     * @param reminderId the unique key of the payment reminder.
     * @param userId     the key to identify the user associated with the reminder.
     * @param amount     the amount due in the payment.
     * @param dueDate    the due date of the payment.
     * @param status     the status of the payment reminder.
     * @return true if the payment reminder was added successfully, false otherwise.
     */
    public boolean addPaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
        try {
            return dbConnection.addPaymentReminder(reminderId, userId, amount, new java.sql.Date(dueDate.getTime()), status);
        } catch (SQLException e) {
            System.err.println("Failed to add payment reminder with ID: " + reminderId);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete a payment reminder.
     *
     * @param reminderId the unique ID to identify the record to be deleted.
     * @return true if the payment reminder was deleted successfully, false otherwise.
     */
    public boolean deletePaymentReminder(String reminderId) {
        try {
            return dbConnection.deletePaymentReminder(reminderId);
        } catch (SQLException e) {
            System.err.println("Failed to delete payment reminder with ID: " + reminderId);
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Delete all payment reminders corresponding to a user.
     *
     * @param userId the unique ID to find and delete all reminders for a user.
     * @return the number of reminders deleted.
     */
    public Integer deleteAllUserReminders(String userId) {
        try {
            return dbConnection.deleteAllUserReminders(userId);
        } catch (SQLException e) {
            System.err.println("Failed to delete all reminders for user ID: " + userId);
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Fetch all payment reminders for a given user with due date in the past or within the next 3 days.
     *
     * @param userId the unique ID to identify the user.
     * @return a list of payment reminders corresponding to the given user.
     */
    public ArrayList<PaymentReminder> fetchPaymentReminders(String userId) {
        ArrayList<PaymentReminder> paymentReminders = new ArrayList<>();
        try (Connection connection = dbConnection.getConnection();
             ResultSet resultSet = dbConnection.fetchPaymentReminders(userId)) {

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
            System.err.println("Failed to fetch payment reminders for user ID: " + userId);
            e.printStackTrace();
        }
        return paymentReminders;
    }
}
