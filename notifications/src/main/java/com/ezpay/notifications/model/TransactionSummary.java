package com.ezpay.notifications.model;

import java.util.List;
/*
  Name: Jai Singh
  Date: 11/08/2024
 **/

/**
  Class to hold the transaction summary details.This class encapsulates the information about completed and 
  incomplete transactions for a specific user.
 */
public class TransactionSummary {
    // Number of completed transactions
    private int completedCount;

    // Number of incomplete transactions
    private int incompleteCount;

    // List of IDs of completed transactions
    private List<Integer> completedTransactions;

    // List of IDs of incomplete transactions
    private List<Integer> incompleteTransactions;

    /**
      Constructor to initialize the TransactionSummary object with the given values.
     
     ** @param completedCount The number of completed transactions.
     ** @param incompleteCount The number of incomplete transactions.
     ** @param completedTransactions The list of IDs for completed transactions.
     ** @param incompleteTransactions The list of IDs for incomplete transactions.
     */
    public TransactionSummary(int completedCount, int incompleteCount, List<Integer> completedTransactions, List<Integer> incompleteTransactions) {
        this.completedCount = completedCount;
        this.incompleteCount = incompleteCount;
        this.completedTransactions = completedTransactions;
        this.incompleteTransactions = incompleteTransactions;
    }

    /**
      Gets the number of completed transactions.
     ** @return The number of completed transactions.
     */
    public int getCompletedCount() {
        return completedCount;
    }

    /**
     ** Sets the number of completed transactions.
     ** @param completedCount The number of completed transactions.
     */
    public void setCompletedCount(int completedCount) {
        this.completedCount = completedCount;
    }

    /**
      Gets the number of incomplete transactions.
     *
     ** @return The number of incomplete transactions.
     */
    public int getIncompleteCount() {
        return incompleteCount;
    }

    /**
     Sets the number of incomplete transactions.
     *
     ** @param incompleteCount The number of incomplete transactions.
     */
    public void setIncompleteCount(int incompleteCount) {
        this.incompleteCount = incompleteCount;
    }

    /**
     Gets the list of IDs for completed transactions.
     *
     ** @return The list of completed transaction IDs.
     */
    public List<Integer> getCompletedTransactions() {
        return completedTransactions;
    }

    /**
     Sets the list of IDs for completed transactions.
     *
     ** @param completedTransactions The list of completed transaction IDs.
     */
    public void setCompletedTransactions(List<Integer> completedTransactions) {
        this.completedTransactions = completedTransactions;
    }

    /**
     Gets the list of IDs for incomplete transactions.
     *
     ** @return The list of incomplete transaction IDs.
     */
    public List<Integer> getIncompleteTransactions() {
        return incompleteTransactions;
    }

    /**
      Sets the list of IDs for incomplete transactions.
     *
     ** @param incompleteTransactions The list of incomplete transaction IDs.
     */
    public void setIncompleteTransactions(List<Integer> incompleteTransactions) {
        this.incompleteTransactions = incompleteTransactions;
    }

    /**
     ** Provides a string representation of the transaction summary.
     ** This method returns a readable format of the transaction summary including
     ** the counts and IDs of completed and incomplete transactions.
     *
     ** @return A string representation of the transaction summary.
     */
    @Override
    public String toString() {
        return "Transaction Summary: " +
               "\nCompleted Transactions Count: " + completedCount +
               "\nIncomplete Transactions Count: " + incompleteCount +
               "\nCompleted Transactions IDs: " + completedTransactions +
               "\nIncomplete Transactions IDs: " + incompleteTransactions;
    }
}
