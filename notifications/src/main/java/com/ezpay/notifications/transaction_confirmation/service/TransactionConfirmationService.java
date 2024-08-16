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
	public TransactionConfirmation hasCompletedTransactionService(TransactionConfirmation transactionConfirmation) {
		//return transactionRepoConfirmation.hasCompletedTransaction(userId, transactionId);
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.hasCompletedTransaction(transactionConfirmation);
		}
		else {
			System.out.print("Null Value");
			return null;
		}
	}
	
	/* Check if the user with userId `userId` has enabled notifications */
	public TransactionConfirmation hasEnabledNotitificationService(TransactionConfirmation transactionConfirmation) {
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.hasEnabledNotification(transactionConfirmation);
		}
		else {
			System.out.println("Null Values");
			return null;
		}
		
	}
	
	/* Getting the Confirmation Message for the Transaction with `transactionId` performed by the User `userId` */
	public String getConfirmationMessageService(TransactionConfirmation transactionConfirmation) {
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.getConfirmationMessage(transactionConfirmation);
		}
		else {
			System.out.println("Null Values");
			return null;
		}
    }
	
	/* Getting the Number of Transaction Completed by the User with `userId` */
	public Integer numCompletedTransactionService(TransactionConfirmation transactionConfirmation) {
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.numCompletedTransaction(transactionConfirmation);
		}
		else {
			System.out.println("Null Value");
			return null;
		}
		
    }
	
	/* Getting all the Transaction Completed by the User with `userId` */
	public TransactionSummary getTransactionSummaryService(TransactionConfirmation transactionConfirmation){
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.getTransactionSummary(transactionConfirmation);
		}
		else {
			System.out.println("Null Values");
			return null;
		}
		
	}
	
	/* Check whether the User with `userId` received the Notification or not */
	public String hasReceivedNotificationService(TransactionConfirmation transactionConfirmation) {
		if(transactionConfirmation!=null) {
			return transactionRepoConfirmation.hasReceivedNotification(transactionConfirmation);
		}
		else {
			System.out.println("Null Values");
			return null;
		}
		
	}	

}
