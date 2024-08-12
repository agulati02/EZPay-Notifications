package com.ezpay.TransactionConfirmation.service;
import java.util.List;

import com.ezpay.TransactionConfirmation.model.TransactionConfirmation;
import com.ezpay.TransactionConfirmation.repo.*;

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
	
	TransactionConfirmationRepository trRepoConfirmation;
	
	public TransactionConfirmationService() {
		trRepoConfirmation=new TransactionConfirmationRepository();
	}
	
	/* Check if the user with userId `uId` has completed the transaction with transactionId `tId` */
	public TransactionConfirmation hasCompletedTransactionService(Integer uId,Integer tId) {
		return trRepoConfirmation.hasCompletedTransaction(uId, tId);
	}
	
	/* Check if the user with userId `uId` has enabled notifications */
	public TransactionConfirmation hasEnabledNotitificationService(Integer uId) {
		return trRepoConfirmation.hasEnabledNotification(uId);
	}
	
	/* Getting the Confirmation Message for the Transaction with `tId` performed by the User `uId` */
	public String getConfirmationMessageService(Integer uId,Integer tId) {
		return trRepoConfirmation.getConfirmationMessage(uId, tId);
    }
	
	/* Getting the Number of Transaction Completed by the User with `uId` */
	public Integer numCompletedTransactionService(Integer uId) {
		return trRepoConfirmation.numCompletedTransaction(uId);
    }
	
	/* Getting all the Transaction Completed by the User with `uId` */
	public List<Integer> completedTransactionService(Integer uId){
		return trRepoConfirmation.completedTransaction(uId);
	}
	
	/* Check whether the User with `uId` received the Notification or not */
	public boolean hasReceivedNotificationService(Integer uId,Integer tId) {
		return trRepoConfirmation.hasReceivedNotification(uId, tId);
	}	

}
