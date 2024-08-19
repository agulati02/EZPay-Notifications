package com.ezpay.notifications.repo;

import java.sql.*;

public class DBConnectionTransactionReminder {
	
	private static final String url = "jdbc:oracle:thin:@//localhost:1522/xe"; 
	private static final String username = "system"; 
	private static final String password = "natwest123"; 
    
	public static Connection getConnection() throws ClassNotFoundException, SQLException {        
        
        // Load and register Oracle JDBC driver
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("Oracle JDBC Driver not found");
        }
        
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
        
	}

}


