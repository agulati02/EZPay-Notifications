package com.ezpay.TransactionConfirmation.repo;
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
import com.ezpay.TransactionConfirmation.model.TransactionConfirmation;

public class TransactionConfirmationRepository {
    
    List<TransactionConfirmation> transactionConfirmation;
    
    {
        transactionConfirmation = new ArrayList<TransactionConfirmation>();
        transactionConfirmation.add(new TransactionConfirmation(1, false, false, "Transaction Incompleted", 2, false));
        transactionConfirmation.add(new TransactionConfirmation(2, true, true, "Transaction Completed", 4, true));
        transactionConfirmation.add(new TransactionConfirmation(3,false,true,"Transaction InCompleted",7,true));
        transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Incompleted", 5, false));
        transactionConfirmation.add(new TransactionConfirmation(1, true, false, "Transaction Incompleted", 15, true));
        
    }
    
    /* Check if the user with userId `uId` has completed the transaction with transactionId `tId` */
    public TransactionConfirmation hasCompletedTransaction(Integer uId, Integer tId) {
        for (TransactionConfirmation tr : transactionConfirmation) {
            // Use equals to compare Integer values
            if (tr.getUserId().equals(uId) && tr.getTransactionId().equals(tId)) {
                if (tr.getHasCompleted()) {
                    return tr;
                } else {
                    return null;
                }
            }
        }
        return null;
    }
    
    /* Check if the user with userId `uId` has enabled notifications */
    public TransactionConfirmation hasEnabledNotification(Integer uId) {
        for (TransactionConfirmation tr : transactionConfirmation) {
            // Use equals to compare Integer values
            if (tr.getUserId().equals(uId)) {
                if (tr.getEnabledNotification()) {
                    return tr;
                }
            }
        }
        return null;
    }
    
    /* Getting the Confirmation Message for the Transaction with `tId` performed by the User `uId` */
    public String getConfirmationMessage(Integer uId,Integer tId) {
    	for(TransactionConfirmation tr:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(tr.getUserId().equals(uId) && tr.getTransactionId().equals(tId)) {
    			if(tr.getHasCompleted()) {
    				return tr.getConfMessage();
    			}
    			else {
    				return tr.getConfMessage();
    			}
    		}
    	}
    	return "Transaction not performed by User";
    }
    
    /* Getting the Number of Transaction Completed by the User with `uId` */
    public Integer numCompletedTransaction(Integer uId) {
    	Integer count=0;
    	for(TransactionConfirmation tr:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(tr.getUserId().equals(uId)) {
    			if(tr.getHasCompleted()) {
    				count++;
    			}
    		}
    	}
    	return count;
    }
    
    /* Getting all the Transaction Completed by the User with `uId` */
    public List<Integer> completedTransaction(Integer uId){
    	List<Integer> comTrans = new ArrayList<Integer>();
    	for(TransactionConfirmation tr:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(tr.getUserId().equals(uId)) {
    			if(tr.getHasCompleted()) {
    				comTrans.add(tr.getTransactionId());
    			}
    		}
    	}
    	return comTrans;
    }
    
    /* Check whether the User with `uId` received the Notification or not */
    public boolean hasReceivedNotification(Integer uId,Integer tId) {
    	for(TransactionConfirmation tr:transactionConfirmation) {
    		// Use equals to compare Integer values
    		if(tr.getUserId().equals(uId)  && tr.getTransactionId().equals(tId)) {
    			if(tr.getHasReceived()) {
    				return true;
    			}
    		}
    	}
    	return false;	
    }
    
}
