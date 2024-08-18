package com.ezpay.notifications.controller;

import com.ezpay.notifications.service.TransactionConfirmationService;
import com.ezpay.notifications.model.TransactionSummary;
import com.ezpay.notifications.model.TransactionConfirmation;
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
    public static void main(String args[]) throws Exception, IOException {
        /*
          Transaction Confirmation Inputs by the User*/
        TransactionConfirmationService transConfService = new TransactionConfirmationService();
        System.out.println("Enter the Transaction Confirmation Details");
        BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the User Id");
        Integer userId = Integer.parseInt(buffReader.readLine());

        while (true) {
            System.out.println("Menu");
            System.out.println("1. Has the user completed the transaction?");
            System.out.println("2. Has the user enabled the notification?");
            System.out.println("3. Get the Confirmation Messages of the Transaction by the User with above id");
            System.out.println("4. Number of Transaction Completed by the User with above Id");
            System.out.println("5. Transaction Summary of all Transaction performed by User");
            System.out.println("6. Has the Transaction Notification been received by the User or not?");
            System.out.println("7. Exit");
            Integer choice = Integer.parseInt(buffReader.readLine());

            switch (choice) {
                case 1:
                    System.out.println("Enter the Transaction Id");
                    Integer transactionId = Integer.parseInt(buffReader.readLine());
                    TransactionConfirmation transactionConfirmation = new TransactionConfirmation(userId, transactionId);
                    if (transactionConfirmation != null) {
                        if (transConfService.hasCompletedTransactionService(transactionConfirmation) != null) {
                            System.out.println("Transaction Completed");
                        } else {
                            System.out.println("Transaction Not Completed");
                        }
                    }
                    break;

                case 2:
                    transactionConfirmation = new TransactionConfirmation(userId);
                    if (transConfService.hasEnabledNotitificationService(transactionConfirmation) != null) {
                        System.out.println("User " + transactionConfirmation.getUserId() + " has enabled the notification.");
                    } else {
                        System.out.println("User " + transactionConfirmation.getUserId() + " has not enabled the notification.");
                    }
                    break;

                case 3:
                    System.out.println("Enter the Transaction Id");
                    transactionId = Integer.parseInt(buffReader.readLine());
                    transactionConfirmation = new TransactionConfirmation(userId, transactionId);
                    System.out.println(transConfService.getConfirmationMessageService(transactionConfirmation));
                    break;

                case 4:
                    transactionConfirmation = new TransactionConfirmation(userId);
                    System.out.println("Number of Transactions Completed by " + transactionConfirmation.getUserId() + " is " + transConfService.numCompletedTransactionService(transactionConfirmation));
                    break;

                case 5:
                    transactionConfirmation = new TransactionConfirmation(userId);
                    TransactionSummary transactionSummaryResult = transConfService.getTransactionSummaryService(transactionConfirmation);
                    if (transactionSummaryResult != null) {
                        System.out.println(transactionSummaryResult.toString());
                    } else {
                        System.out.println("No Transaction Performed by the User");
                    }
                    break;

                case 6:
                    System.out.println("Enter the Transaction Id");
                    transactionId = Integer.parseInt(buffReader.readLine());
                    transactionConfirmation = new TransactionConfirmation(userId, transactionId);
                    System.out.println(transConfService.hasReceivedNotificationService(transactionConfirmation));
                    break;

                case 7:
                    System.out.println("Exit");
                    return;

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
