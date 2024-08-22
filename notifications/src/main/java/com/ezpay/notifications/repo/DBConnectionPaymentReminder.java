package com.ezpay.notifications.repo;

/*
 * Author Name: Doneela Das
 * Date Details: 19/08/2024
 */

/*
 * Class Description:
 * The DBConnectionPaymentReminder class is responsible for establishing and managing
 * a connection to an Oracle database. This class provides methods to add, delete,
 * fetch payment reminders, and manage database connections. It ensures that necessary
 * database operations are performed with proper exception handling and connection management.
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

    /**
     * Constructor to initialize the database connection.
     * @throws ClassNotFoundException if the Oracle JDBC Driver class is not found.
     * @throws SQLException if there is an error while establishing the connection.
     */
    public DBConnectionPaymentReminder() throws ClassNotFoundException, SQLException {
        try {
            // Load the JDBC driver
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Establish the connection
            this.connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new ClassNotFoundException("Oracle JDBC Driver not found", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to establish a database connection", e);
        }
    }

    /**
     * Gets the current database connection.
     * @return the Connection object.
     */
    public Connection getConnection() {
        return this.connection;
    }

    /**
     * Closes the database connection if it is not already closed.
     */
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Failed to close database connection: " + e.getMessage());
            // Log the error using a logger if available
        }
    }

    /**
     * Adds a payment reminder to the database.
     * @param reminderId the ID of the payment reminder.
     * @param userId the ID of the user.
     * @param amount the amount of the payment reminder.
     * @param dueDate the due date of the payment reminder.
     * @param status the status of the payment reminder.
     * @return true if the reminder was added successfully, false otherwise.
     * @throws SQLException if there is an error while adding the reminder.
     */
    public boolean addPaymentReminder(String reminderId, String userId, Double amount, java.sql.Date dueDate,
                                       String status) throws SQLException {
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
            throw new SQLException("Failed to add payment reminder", e);
        }
    }

    /**
     * Deletes a payment reminder from the database.
     * @param reminderId the ID of the payment reminder to be deleted.
     * @return true if the reminder was deleted successfully, false otherwise.
     * @throws SQLException if there is an error while deleting the reminder.
     */
    public boolean deletePaymentReminder(String reminderId) throws SQLException {
        String query = "DELETE FROM PaymentReminder WHERE reminderId = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, reminderId);

            int result = preparedStatement.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            throw new SQLException("Failed to delete payment reminder", e);
        }
    }

    /**
     * Fetches payment reminders for a user with due date in the past or within the next 3 days.
     * @param userId the ID of the user whose reminders are to be fetched.
     * @return a ResultSet containing the payment reminders.
     * @throws SQLException if there is an error while fetching the reminders.
     */
    public ResultSet fetchPaymentReminders(String userId) throws SQLException {
        String query = "SELECT * FROM PaymentReminder WHERE userId = ? AND dueDate <= SYSDATE + INTERVAL '3' DAY";
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setString(1, userId);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new SQLException("Failed to fetch payment reminders", e);
        }
    }

    /**
     * Deletes all payment reminders for a specific user.
     * @param userId the ID of the user whose reminders are to be deleted.
     * @return the number of reminders deleted.
     * @throws SQLException if there is an error while deleting the reminders.
     */
    public int deleteAllUserReminders(String userId) throws SQLException {
        String query = "DELETE FROM PaymentReminder WHERE userId = ?";
        try (PreparedStatement preparedStatement = this.connection.prepareStatement(query)) {
            preparedStatement.setString(1, userId);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Failed to delete all reminders for user", e);
        }
    }
}
