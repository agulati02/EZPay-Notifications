package com.ezpay.notifications.transaction_confirmation.service;

import com.ezpay.notifications.transaction_confirmation.model.TransactionSummary;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import com.ezpay.notifications.transaction_confirmation.model.TransactionConfirmation;

public class TransactionConfirmationServiceTest {
	static TransactionConfirmationService transactionConfirmationService;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		transactionConfirmationService = new TransactionConfirmationService();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		transactionConfirmationService=null;
	}
	
	// Test for a scenario where the user has completed a transaction.
    @Test
    public void testHasCompletedTransactionServiceSuccess() {
        Integer userId = 1;
        Integer transactionId = 5;

        TransactionConfirmation result = transactionConfirmationService.hasCompletedTransactionService(userId, transactionId);

        assertNotNull(result);
        assertTrue(result.getHasCompleted());
    }

    // Test for a scenario where the user has not completed a transaction.
    @Test
    public void testHasCompletedTransactionServiceFailure() {
        Integer userId = 1;
        Integer transactionId = 2;

        TransactionConfirmation result = transactionConfirmationService.hasCompletedTransactionService(userId, transactionId);

        assertNull(result);
    }

    // Test for a scenario where the user has enabled notifications.
    @Test
    public void testHasEnabledNotitificationServiceSuccess() {
        Integer userId = 3;

        TransactionConfirmation result = transactionConfirmationService.hasEnabledNotitificationService(userId);

        assertNotNull(result);
        assertTrue(result.getEnabledNotification());
    }

    // Test for a scenario where the user has not enabled notifications.
    @Test
    public void testHasEnabledNotitificationServiceFailure() {
        Integer userId = 1;

        TransactionConfirmation result = transactionConfirmationService.hasEnabledNotitificationService(userId);

        assertNull(result);
    }

    // Test for retrieving the confirmation message when the transaction is completed.
    @Test
    public void testGetConfirmationMessageServiceSuccess() {
        Integer userId = 1;
        Integer transactionId = 15;

        String result = transactionConfirmationService.getConfirmationMessageService(userId, transactionId);

        assertNotEquals("Transaction not performed by User", result);
    }

    // Test for retrieving the confirmation message when the transaction ID doesn't match.
    @Test
    public void testGetConfirmationMessageServiceFailure() {
        Integer userId = 1;
        Integer transactionId = 99;  // Non-existent transaction ID

        String result = transactionConfirmationService.getConfirmationMessageService(userId, transactionId);

        assertEquals("Transaction not performed by User", result);
    }

    // Test for the number of completed transactions for a user.
    @Test
    public void testNumCompletedTransactionServiceSuccess() {
        Integer userId = 1;

        Integer result = transactionConfirmationService.numCompletedTransactionService(userId);

        assertEquals(Integer.valueOf(2), result);
    }

    // Test for the number of completed transactions for a user with no completed transactions.
    @Test
    public void testNumCompletedTransactionServiceNoCompletedTransactions() {
        Integer userId = 3;  // User with no completed transactions

        Integer result = transactionConfirmationService.numCompletedTransactionService(userId);

        assertEquals(Integer.valueOf(0), result);
    }
    
 // Test for retrieving a summary of completed and incomplete transactions.
    @Test
    public void testGetTransactionSummaryServiceSuccess() {
        Integer userId = 1;

        TransactionSummary result = transactionConfirmationService.getTransactionSummaryService(userId);

        assertEquals(2, result.getCompletedTransactions().size());
        assertEquals(1, result.getIncompleteTransactions().size());
        assertTrue(result.getCompletedTransactions().contains(5));
        assertTrue(result.getCompletedTransactions().contains(15));
    }
    
 // Test for retrieving a summary for a user with no completed transactions.
    @Test
    public void testGetTransactionSummaryServiceNoCompletedTransactions() {
        Integer userId = 4; // User with no completed transactions

        TransactionSummary result = transactionConfirmationService.getTransactionSummaryService(userId);

        assertTrue(result.getCompletedTransactions().isEmpty());
    }


    // Test for checking if the user has received the notification.
    @Test
    public void testHasReceivedNotificationServiceSuccess() {
        Integer userId = 1;
        Integer transactionId = 15;

        boolean result = transactionConfirmationService.hasReceivedNotificationService(userId, transactionId);

        assertTrue(result);
    }

    // Test for checking if the user has not received the notification.
    @Test
    public void testHasReceivedNotificationServiceFailure() {
        Integer userId = 1;
        Integer transactionId = 5;

        boolean result = transactionConfirmationService.hasReceivedNotificationService(userId, transactionId);

        assertFalse(result);
    }

}
