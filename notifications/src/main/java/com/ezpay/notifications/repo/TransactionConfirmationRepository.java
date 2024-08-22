package com.ezpay.notifications.repo;

/*
 * Author Name: Geethapriya Thandavamurthi  
 * Date Details: 19/08/2024 * Monday
 * /
 
/*
 * Module Description 
 The TransactionConfirmationRepository class serves as a data repository for managing 
 TransactionConfirmation objects. It provides methods to interact with the underlying database 
 and perform operations related to transaction confirmations. The key functionalities include
 checking whether a transaction has been completed, whether notifications have been enabled or received by 
 the user, retrieving the confirmation messages, counting or listing completed transactions by a specific user.
 This repository interacts with a database using JDBC to fetch and manipulate transaction data.
 */
import java.util.ArrayList;
import java.util.List;
import com.ezpay.notifications.model.TransactionConfirmation;
import com.ezpay.notifications.model.TransactionSummary;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TransactionConfirmationRepository {
    
	private static final Logger LOGGER = Logger.getLogger(TransactionConfirmationRepository.class.getName());
	
    List<TransactionConfirmation> transactionConfirmation;
    
    
    /*
    userId, hasCompleted, enabledNotification, confirmationMessage, transactionId, hasReceived  
    transactionConfirmation.add(new TransactionConfirmation(1, false, false, "Transaction Incompleted", 2, false));
    transactionConfirmation.add(new TransactionConfirmation(2, true, true, "Transaction Completed", 4, true));
    transactionConfirmation.add(new TransactionConfirmation(3,false,true,"Transaction Incompleted",7,true));
    transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Completed", 5, false));
    transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Completed", 15, true));
    transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Completed", 10, false));
    transactionConfirmation.add(new TransactionConfirmation(1, false, false, "Transaction Incompleted",3, false));
    */
    
    
    /* Check if the user with userId `userId` has completed the transaction with transactionId `transactionId` */
    public TransactionConfirmation hasCompletedTransaction(TransactionConfirmation transConfirmation) {
    	TransactionConfirmation result = null;
    	
    	Integer userId;
    	boolean hasCompletedBool;
    	boolean enabledNotification;
    	String confirmationMessage;
    	Integer transactionId;
    	boolean hasReceived;
    	
    	try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()+" AND transaction_id = "+transConfirmation.getTransactionId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//if resultSet is not null
    		if (resultSet.next()) {
    			
               int hasCompleted = resultSet.getInt("has_completed");
               
               //if hasCompleted is true
               if (hasCompleted == 1) {
            	   hasCompletedBool= true;
            	   userId = Integer.valueOf(resultSet.getInt("user_id"));
            	   enabledNotification = resultSet.getBoolean("enabled_notification");
            	   confirmationMessage = resultSet.getString("conf_message");
            	   transactionId = Integer.valueOf(resultSet.getInt("transaction_id"));
            	   hasReceived = resultSet.getBoolean("has_received");
            	   
                   result = new TransactionConfirmation(userId, hasCompletedBool, enabledNotification, confirmationMessage, transactionId, hasReceived); 
               	}
    		}
    	}catch (Exception e) {
           e.printStackTrace();
           LOGGER.log(Level.SEVERE, "Error checking if transaction is completed for userId: " + transConfirmation.getUserId() + " and transactionId: " + transConfirmation.getTransactionId(), e);
        }

           return result;
    }
    
    /* Check if the user with userId `userId` has enabled notifications */
    public TransactionConfirmation hasEnabledNotification(TransactionConfirmation transConfirmation) {
    	TransactionConfirmation result = null;
    	
    	Integer userId;
    	boolean hasCompleted;
    	boolean isNotificationEnabled;
    	String confirmationMessage;
    	Integer transactionId;
    	boolean hasReceived;
    	
    	try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//if resultSet is not null
    		if (resultSet.next()) {
    			
               int enabledNotification = resultSet.getInt("enabled_notification");
               
               //if enabledNotification is true
               if (enabledNotification == 1) {
            	   
            	   isNotificationEnabled= true;
            	   userId = Integer.valueOf(resultSet.getInt("user_id"));
            	   hasCompleted = resultSet.getBoolean("has_completed");
            	   confirmationMessage = resultSet.getString("conf_message");
            	   transactionId = Integer.valueOf(resultSet.getInt("transaction_id"));
            	   hasReceived = resultSet.getBoolean("has_received");
            	   
                   result = new TransactionConfirmation(userId, hasCompleted, isNotificationEnabled, confirmationMessage, transactionId, hasReceived); 
               	}
    		}
        } catch (Exception e) {
               e.printStackTrace();
               LOGGER.log(Level.SEVERE, "Error checking if notifications are enabled for userId: " + transConfirmation.getUserId(), e);
        }

           return result;
    	
    }
    
    /* Getting the Confirmation Message for the Transaction with `transactionId` performed by the User `userId` */
    public String getConfirmationMessage(TransactionConfirmation transConfirmation) {
    	String result = null;
    	
    	try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()+" AND transaction_id = "+transConfirmation.getTransactionId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//if resultSet is not null
    		if (resultSet.next()) {
    			
               int hasCompleted = resultSet.getInt("has_completed");
               
               //if hasCompleted is true
               if (hasCompleted == 1) {
            	   result = resultSet.getString("conf_message");
            	   return result;
	           }
               else {
            	   result = resultSet.getString("conf_message");
            	   return result;
               }   
    		}
        } catch (Exception e) {
               e.printStackTrace();
               LOGGER.log(Level.SEVERE, "Error retrieving confirmation message for userId: " + transConfirmation.getUserId() + " and transactionId: " + transConfirmation.getTransactionId(), e);
        }
    	
    	result = "Transaction not performed by User";
        return result;
    	
    }
    
    /* Getting the Number of Transaction Completed by the User with `userId` */
    public Integer numCompletedTransaction(TransactionConfirmation transConfirmation) {
    	Integer count = 0;
    	
    	try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//while resultSet is not null
    		while (resultSet.next()) {
    			
               int hasCompleted = resultSet.getInt("has_completed");
               
               //if hasCompleted is true
               if (hasCompleted == 1) {
            	   count++;
	           } 
    		}
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error counting completed transactions for userId: " + transConfirmation.getUserId(), e);
        }
    	
    	return count;
    }
    
    /* Method to get a summary of completed and incomplete transactions for a user*/
    public TransactionSummary getTransactionSummary(TransactionConfirmation transConfirmation) {
    	int completedCount = 0;
        int incompleteCount = 0;
        List<Integer> completedTransactions = new ArrayList<Integer>();
        List<Integer> incompleteTransactions = new ArrayList<Integer>();
        
    	Integer transactionId;
    	
        try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//while resultSet is not null
    		while (resultSet.next()) {
    			
               int hasCompleted = resultSet.getInt("has_completed");
               
               //if hasCompleted is true
               if (hasCompleted == 1) {
            	   transactionId = Integer.valueOf(resultSet.getInt("transaction_id"));
            	   //increment completedCount
            	   completedCount++;
            	   completedTransactions.add(transactionId);
	            }
               else {
            	   transactionId = Integer.valueOf(resultSet.getInt("transaction_id"));
            	   //increment incompleteCount
            	   incompleteCount++;
            	   incompleteTransactions.add(transactionId);
               }
    		}
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error retrieving transaction summary for userId: " + transConfirmation.getUserId(), e);
        }
    	
        //return TransactionSummary
        return new TransactionSummary(completedCount, incompleteCount, completedTransactions, incompleteTransactions);
    }
    
    /* Check whether the User with `userId` for the Transaction with `transactionId` received the Notification or not */
    public String hasReceivedNotification(TransactionConfirmation transConfirmation) {
    
    	try {
    		Connection connection = DBConnectionTransactionConfirmation.getConnection();
    		
    		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM transaction_confirmation WHERE user_id = " + transConfirmation.getUserId()+" AND transaction_id = "+transConfirmation.getTransactionId()); 

    		ResultSet resultSet = preparedStatement.executeQuery();
    		
    		//while resultSet is not null
    		while (resultSet.next()) {
    			
               int hasReceived = resultSet.getInt("has_received");
               
               //if hasReceived is true
               if (hasReceived == 1) {
            	   return "Received the Notification";
	            } 
    		}
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.log(Level.SEVERE, "Error checking notification receipt for userId: " + transConfirmation.getUserId() + " and transactionId: " + transConfirmation.getTransactionId(), e);
        }
    	
    	return "Not received the Notification";
    }
     
}
