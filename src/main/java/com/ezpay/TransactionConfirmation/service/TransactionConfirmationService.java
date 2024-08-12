package com.ezpay.TransactionConfirmation.service;
import java.util.List;

import com.ezpay.TransactionConfirmation.model.TransactionConfirmation;
import com.ezpay.TransactionConfirmation.repo.*;

/*
  Author Name: Jai Singh 
 Date Details: 10/08/2024 ( Saturday )
 * */

/* Module Name 
  The TransactionConfirmationService class serves as a service layer that interacts with the 
  TransactionConfirmationRepository to manage transaction-related operations. It provides methods 
  to check if a user has completed a transaction, enabled notifications, received notifications, and 
  to retrieve confirmation messages. Additionally, it offers functionality to count and list transactions 
  completed by a user. This service layer acts as an intermediary between the controller and repository, 
  facilitating data retrieval and business logic execution.
 * */
public class TransactionConfirmationService {
	
	TransactionConfirmationRepository trRepoConfirmation;
	
	public TransactionConfirmationService() {
		trRepoConfirmation=new TransactionConfirmationRepository();
	}
	
	/* Check if the user with userId `userId` has completed the transaction with transactionId `transactionId` */
	public TransactionConfirmation hasCompletedTransactionService(Integer userId,Integer transactionId) {
		return trRepoConfirmation.hasCompletedTransaction(userId, transactionId);
	}
	
	/* Check if the user with userId `userId` has enabled notifications */
	public TransactionConfirmation hasEnabledNotitificationService(Integer userId) {
		return trRepoConfirmation.hasEnabledNotification(userId);
	}
	
	/* Getting the Confirmation Message for the Transaction with `transactionId` performed by the User `userId` */
	public String getConfirmationMessageService(Integer userId,Integer transactionId) {
		return trRepoConfirmation.getConfirmationMessage(userId, transactionId);
    }
	
	/* Getting the Number of Transaction Completed by the User with `userId` */
	public Integer numCompletedTransactionService(Integer userId) {
		return trRepoConfirmation.numCompletedTransaction(userId);
    }
	
	/* Getting all the Transaction Completed by the User with `userId` */
	public List<Integer> completedTransactionService(Integer userId){
		return trRepoConfirmation.completedTransaction(userId);
	}
	
	/* Check whether the User with `userId` received the Notification or not */
	public boolean hasReceivedNotificationService(Integer userId,Integer transactionId) {
		return trRepoConfirmation.hasReceivedNotification(userId, transactionId);
	}	

}
