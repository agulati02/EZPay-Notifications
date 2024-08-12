package com.ezpay.TransactionConfirmation.controller;
import com.ezpay.TransactionConfirmation.service.TransactionConfirmationService;
import com.ezpay.TransactionConfirmation.model.TransactionSummary;
import java.io.*;
/*
 * Author Name: Jai Singh 
 * Date Details: 10/08/2024 * Saturday 
 * */

/*
 * Module Description 
 The TransactionConfirmationRepository class acts as a data repository for managing 
TransactionConfirmation objects. It maintains a list of transaction confirmations and provides methods 
to check whether a transaction has been completed, whether notifications have been enabled or received by 
the user, to retrieve confirmation messages, and to count or list completed transactions by a specific user.
 The repository uses in-memory storage, making it ideal for testing and prototyping.
 */
public class TransactionConfirmationController {
	public static void main(String args[]) throws Exception,IOException {
		/*
		  Transaction Confirmation Inputs by the User*/
		TransactionConfirmationService transConfService=new TransactionConfirmationService();
		System.out.println("Enter the Transaction Confirmation Details");
		BufferedReader buffReader=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the User Id");
		Integer userId=Integer.parseInt(buffReader.readLine());
		
		while(true) {
			System.out.println("Menu");
			System.out.println("1. Has the user completed the transaction?");
			System.out.println("2. Has the user enabled the notification?");
			System.out.println("3. Get the Confirmation Messages of the Transaction by the User with above id");
			System.out.println("4. Number of Transaction Completed by the User with above Id");
			System.out.println("5. Transaction Summary of all Transaction performed by User");
			System.out.println("6. Has the Transaction Notification been received by the User or not?");
			System.out.println("7. Exit");
			Integer choice=Integer.parseInt(buffReader.readLine());
			
			if(choice==1) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(buffReader.readLine());
				if(transConfService.hasCompletedTransactionService(userId, transactionId)!=null) {
					System.out.println("Transaction Completed");
				}
				else {
					System.out.println("Transaction Not Completed");
				}
			}
			
			if(choice==2) {
				if(transConfService.hasEnabledNotitificationService(userId)!=null) {
					System.out.println("User "+userId+" has enabled the notification.");
				}
				else {
					System.out.println("User "+userId+" has not enabled the notification.");
				}	
			}
			
			if(choice==3) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(buffReader.readLine());
				System.out.println(transConfService.getConfirmationMessageService(userId, transactionId));	
			}
			
			if(choice==4) {
				System.out.println("Number of Transactions Completed by "+ userId + " is "+transConfService.numCompletedTransactionService(userId));
			}
			
			if(choice==5) {
				TransactionSummary result=transConfService.getTransactionSummaryService(userId);
				if(result!=null) {
					System.out.println(result.toString());
				}
				else {
					System.out.println("No Transaction Performed by the User");
				}
				
			}
			
			if(choice==6) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(buffReader.readLine());
				if(transConfService.hasReceivedNotificationService(userId, transactionId)) {
					System.out.println("User "+userId+" has received the notification");
				}
				else {
					System.out.println("User "+userId+" has not received the notification");
				}
			}
			
			if(choice==7) {
				System.out.println("Exit");
				break;
			}
			
		}	
		
	}

}
