package com.ezpay.notifications.repo;

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
import java.util.ArrayList;
import java.util.List;
import com.ezpay.notifications.model.TransactionConfirmation;
import com.ezpay.notifications.model.TransactionSummary;

public class TransactionConfirmationRepository {
    
    List<TransactionConfirmation> transactionConfirmation;
    
    {
        transactionConfirmation = new ArrayList<TransactionConfirmation>();
        transactionConfirmation.add(new TransactionConfirmation(1, false, false, "Transaction Incompleted", 2, false));
        transactionConfirmation.add(new TransactionConfirmation(2, true, true, "Transaction Completed", 4, true));
        transactionConfirmation.add(new TransactionConfirmation(3,false,true,"Transaction InCompleted",7,true));
        transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Completed", 5, false));
        transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Completed", 15, true));
        
    }
    
    /* Check if the user with userId `userId` has completed the transaction with transactionId `transactionId` */
    public TransactionConfirmation hasCompletedTransaction(TransactionConfirmation transConfirmation) {
        for (TransactionConfirmation transactionConfirmation : transactionConfirmation) {
            // Use equals to compare Integer values
            if (transactionConfirmation.getUserId().equals(transConfirmation.getUserId()) && transactionConfirmation.getTransactionId().equals(transConfirmation.getTransactionId())) {
                if (transactionConfirmation.getHasCompleted()) {
                    return transactionConfirmation;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
    
    /* Check if the user with userId `userId` has enabled notifications */
    public TransactionConfirmation hasEnabledNotification(TransactionConfirmation transConfirmation) {
        for (TransactionConfirmation transactionConfirmation : transactionConfirmation) {
            // Use equals to compare Integer values
            if (transactionConfirmation.getUserId().equals(transConfirmation.getUserId())) {
                if (transactionConfirmation.getEnabledNotification()) {
                    return transactionConfirmation;
                }
            }
        }
        return null;
    }
    
    /* Getting the Confirmation Message for the Transaction with `transactionId` performed by the User `userId` */
    public String getConfirmationMessage(TransactionConfirmation transConfirmation) {
    	for(TransactionConfirmation transactionConfirmation:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(transactionConfirmation.getUserId().equals(transConfirmation.getUserId()) && transactionConfirmation.getTransactionId().equals(transConfirmation.getTransactionId())) {
    			if(transactionConfirmation.getHasCompleted()) {
    				return transactionConfirmation.getConfMessage();
    			}
    			else {
    				return transactionConfirmation.getConfMessage();
    			}
    		}
    	}
    	return "Transaction not performed by User";
    }
    
    /* Getting the Number of Transaction Completed by the User with `userId` */
    public Integer numCompletedTransaction(TransactionConfirmation transConfirmation) {
    	Integer count=0;
    	for(TransactionConfirmation transactionConfirmation:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(transactionConfirmation.getUserId().equals(transConfirmation.getUserId())) {
    			if(transactionConfirmation.getHasCompleted()) {
    				count++;
    			}
    		}
    	}
    	return count;
    }
    
    
    /* Method to get a summary of completed and incomplete transactions for a user*/
    public TransactionSummary getTransactionSummary(TransactionConfirmation transConfirmation) {
        int completedCount = 0;
        int incompleteCount = 0;
        List<Integer> completedTransactions = new ArrayList<Integer>();
        List<Integer> incompleteTransactions = new ArrayList<Integer>();

        for (TransactionConfirmation transactionConfirmation : transactionConfirmation) {
            if (transactionConfirmation.getUserId().equals(transConfirmation.getUserId())) {
                if (transactionConfirmation.getHasCompleted()) {
                    completedCount++;
                    completedTransactions.add(transactionConfirmation.getTransactionId());
                } else {
                    incompleteCount++;
                    incompleteTransactions.add(transactionConfirmation.getTransactionId());
                }
            }
        }

        return new TransactionSummary(completedCount, incompleteCount, completedTransactions, incompleteTransactions);
    }
    
    /* Check whether the User with `userId` received the Notification or not */
    public String hasReceivedNotification(TransactionConfirmation transConfirmation) {
    	for(TransactionConfirmation transactionConfirmation:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(transactionConfirmation.getUserId().equals(transConfirmation.getUserId())  && transactionConfirmation.getTransactionId().equals(transConfirmation.getTransactionId())) {
    			if(transactionConfirmation.getHasReceived()) {
    				return "Received the Notification";
    			}
    		}
    	}
    	return "Not Received the Notification";	
    }
    
    
}
