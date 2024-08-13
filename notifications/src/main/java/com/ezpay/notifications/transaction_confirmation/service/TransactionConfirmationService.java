package com.ezpay.notifications.transaction_confirmation.service;

import com.ezpay.notifications.transaction_confirmation.model.TransactionConfirmation;
import com.ezpay.notifications.transaction_confirmation.repo.*;
import com.ezpay.notifications.transaction_confirmation.model.TransactionSummary;

/*
 * Author Name: Jai Singh 
 * Date Details: 10/08/2024 * Saturday 
 * */

/* Module Name 
 * The TransactionConfirmationService class serves as a service layer that interacts with the 
 * TransactionConfirmationRepository to manage transaction-related operations. It provides methods 
 * to check if a user has completed a transaction, enabled notifications, received notifications, and 
 * to retrieve confirmation messages. Additionally, it offers functionality to count and list transactions 
 * completed by a user. This service layer acts as an intermediary between the controller and repository, 
 * facilitating data retrieval and business logic execution.
 * */
public class TransactionConfirmationService {
	
	TransactionConfirmationRepository transactionRepoConfirmation;
	
	public TransactionConfirmationService() {
		transactionRepoConfirmation=new TransactionConfirmationRepository();
	}
	
	/* Check if the user with userId `userId` has completed the transaction with transactionId `transactionId` */
	public TransactionConfirmation hasCompletedTransactionService(Integer userId,Integer transactionId) {
		return transactionRepoConfirmation.hasCompletedTransaction(userId, transactionId);
	}
	
	/* Check if the user with userId `userId` has enabled notifications */
	public TransactionConfirmation hasEnabledNotitificationService(Integer userId) {
		return transactionRepoConfirmation.hasEnabledNotification(userId);
	}
	
	/* Getting the Confirmation Message for the Transaction with `transactionId` performed by the User `userId` */
	public String getConfirmationMessageService(Integer userId,Integer transactionId) {
		return transactionRepoConfirmation.getConfirmationMessage(userId, transactionId);
    }
	
	/* Getting the Number of Transaction Completed by the User with `userId` */
	public Integer numCompletedTransactionService(Integer userId) {
		return transactionRepoConfirmation.numCompletedTransaction(userId);
    }
	
	/* Getting all the Transaction Completed by the User with `userId` */
	public TransactionSummary getTransactionSummaryService(Integer userId){
		return transactionRepoConfirmation.getTransactionSummary(userId);
	}
	
	/* Check whether the User with `userId` received the Notification or not */
	public boolean hasReceivedNotificationService(Integer userId,Integer transactionId) {
		return transactionRepoConfirmation.hasReceivedNotification(userId, transactionId);
	}	

}