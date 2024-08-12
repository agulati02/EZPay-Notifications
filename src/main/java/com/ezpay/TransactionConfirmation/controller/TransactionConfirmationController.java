package com.ezpay.TransactionConfirmation.controller;
import com.ezpay.TransactionConfirmation.model.TransactionConfirmation;
import com.ezpay.TransactionConfirmation.service.TransactionConfirmationService;
import java.io.*;
import java.util.List;
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
		TransactionConfirmationService service=new TransactionConfirmationService();
		System.out.println("Enter the Transaction Confirmation Details");
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the User Id");
		Integer userId=Integer.parseInt(br.readLine());
		
		while(true) {
			System.out.println("Menu");
			System.out.println("1. Has the user completed the transaction?");
			System.out.println("2. Has the user enabled the notification?");
			System.out.println("3. Get the Confirmation Messages of the Transaction by the User with above id");
			System.out.println("4. Number of Transaction Completed by the User with above Id");
			System.out.println("5. List of Transactions which are Completed by the User with above Id");
			System.out.println("6. Has the Transaction Notification been received by the User or not?");
			System.out.println("7. Exit");
			Integer opt=Integer.parseInt(br.readLine());
			
			if(opt==1) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(br.readLine());
				if(service.hasCompletedTransactionService(userId, transactionId)!=null) {
					System.out.println("Transaction Completed");
				}
				else {
					System.out.println("Transaction Not Completed");
				}
			}
			
			if(opt==2) {
				if(service.hasEnabledNotitificationService(userId)!=null) {
					System.out.println("User "+userId+" has enabled the notification.");
				}
				else {
					System.out.println("User "+userId+" has not enabled the notification.");
				}	
			}
			
			if(opt==3) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(br.readLine());
				System.out.println(service.getConfirmationMessageService(userId, transactionId));	
			}
			
			if(opt==4) {
				System.out.println("Number of Transactions Completed by "+ userId + " is "+service.numCompletedTransactionService(userId));
			}
			
			if(opt==5) {
				List<Integer> res=service.completedTransactionService(userId);
				if(res.size()==0) {
					System.out.println("No Transaction Completed by "+userId);
				}
				else {
					System.out.println("List of Transactions Completed by "+userId);
					for(int i=0;i<res.size();i++) {
						System.out.println("Transaction Id "+res.get(i));
					}
				}
			}
			
			if(opt==6) {
				System.out.println("Enter the Transaction Id");
				Integer transactionId=Integer.parseInt(br.readLine());
				if(service.hasReceivedNotificationService(userId, transactionId)) {
					System.out.println("User "+userId+" has received the notification");
				}
				else {
					System.out.println("User "+userId+" has not received the notification");
				}
			}
			
			if(opt==7) {
				System.out.println("Exit");
				break;
			}
			
		}	
		
	}

}
