package com.ezpay.notifications.repo;

/*
 * Author Name: Geethapriya Thandavamurthi  
 * Date Details: 19/08/2024 * Monday 
 */

/*
 * Module Description 
 The DBConnectionTransactionConfirmation class establishes a connection to an Oracle database, 
 ensuring the necessary driver is loaded. It’s used for handling transaction confirmations in 
 the EZPay notifications service, providing a reliable way to interact with the database for 
 these critical operations.
 */

import java.sql.*;

public class DBConnectionTransactionConfirmation {
	
	private static final String DB_URL = "jdbc:oracle:thin:@//localhost:1522/xe"; 
	private static final String DB_USERNAME = "system"; 
	private static final String DB_PASSWORD = "natwest123"; 
    
	public static Connection getConnection() throws ClassNotFoundException, SQLException {        
        
        // Load and register Oracle JDBC driver
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Oracle JDBC Driver not found");
        }

	// Establish the connection
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        return connection;
        
	}

}


