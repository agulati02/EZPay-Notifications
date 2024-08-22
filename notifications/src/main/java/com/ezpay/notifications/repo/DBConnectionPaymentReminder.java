package com.ezpay.notifications.repo;

/*
* Author Name: Doneela Das
* Date Details: 19/08/2024 
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnectionPaymentReminder {

	// Database URL, username, and password
	private static final String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String DB_USER = "system";
	private static final String DB_PASSWORD = "admin";

	// Initialize the connection object
	private Connection connection;

	public DBConnectionPaymentReminder() {
		try {
			// Load the JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// Establish the connection
			this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		return this.connection;
	}

	public void closeConnection() {
		try {
			if (this.connection != null && !this.connection.isClosed()) {
				this.connection.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Method to add a payment reminder
	public boolean addPaymentReminder(String reminderId, String userId, Double amount, java.sql.Date dueDate,
			String status) {
		String query = "INSERT INTO PaymentReminder (reminderId, userId, amount, dueDate, status) VALUES (?, ?, ?, ?, ?)";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
			preparedStatement.setString(1, reminderId);
			preparedStatement.setString(2, userId);
			preparedStatement.setDouble(3, amount);
			preparedStatement.setDate(4, dueDate);
			preparedStatement.setString(5, status);

			int result = preparedStatement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Method to delete a payment reminder
	public boolean deletePaymentReminder(String reminderId) {
		String query = "DELETE FROM PaymentReminder WHERE reminderId = ?";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
			preparedStatement.setString(1, reminderId);

			int result = preparedStatement.executeUpdate();
			return result > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	// Method to fetch payment reminders for a user with due date in the past or
	// within the next 3 days
	public ResultSet fetchPaymentReminders(String userId) {
		String query = "SELECT * FROM PaymentReminder WHERE userId = ? AND dueDate <= SYSDATE + INTERVAL '3' DAY";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(query);
			preparedStatement.setString(1, userId);
			return preparedStatement.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Method to delete all reminders for a user
	public int deleteAllUserReminders(String userId) {
		String query = "DELETE FROM PaymentReminder WHERE userId = ?";
		try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
			preparedStatement.setString(1, userId);
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}
}
