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


        TransactionConfirmation result = transactionConfirmationService.hasCompletedTransactionService(1,5);

        assertNotNull(result);
        assertTrue(result.getHasCompleted());
    }

    // Test for a scenario where the user has not completed a transaction.
    @Test
    public void testHasCompletedTransactionServiceFailure() {

        TransactionConfirmation result = transactionConfirmationService.hasCompletedTransactionService(1,2);

        assertNull(result);
    }

    // Test for a scenario where the user has enabled notifications.
    @Test
    public void testHasEnabledNotitificationServiceSuccess() {

        TransactionConfirmation result = transactionConfirmationService.hasEnabledNotitificationService(3);

        assertNotNull(result);
        assertTrue(result.getEnabledNotification());
    }

    // Test for a scenario where the user has not enabled notifications.
    @Test
    public void testHasEnabledNotitificationServiceFailure() {

        TransactionConfirmation result = transactionConfirmationService.hasEnabledNotitificationService(1);

        assertNull(result);
    }

    // Test for retrieving the confirmation message when the transaction is completed.
    @Test
    public void testGetConfirmationMessageServiceSuccess() {

        String result = transactionConfirmationService.getConfirmationMessageService(1,15);

        assertNotEquals("Transaction not performed by User", result);
    }

    // Test for retrieving the confirmation message when the transaction ID doesn't match.
    @Test
    public void testGetConfirmationMessageServiceFailure() {

        String result = transactionConfirmationService.getConfirmationMessageService(1,99);

        assertEquals("Transaction not performed by User", result);
    }

    // Test for the number of completed transactions for a user.
    @Test
    public void testNumCompletedTransactionServiceSuccess() {

        Integer result = transactionConfirmationService.numCompletedTransactionService(1);

        assertEquals(Integer.valueOf(2), result);
    }

    // Test for the number of completed transactions for a user with no completed transactions.
    @Test
    public void testNumCompletedTransactionServiceNoCompletedTransactions() {

        Integer result = transactionConfirmationService.numCompletedTransactionService(3);

        assertEquals(Integer.valueOf(0), result);
    }
    
 // Test for retrieving a summary of completed and incomplete transactions.
    @Test
    public void testGetTransactionSummaryServiceSuccess() {
	    
        TransactionSummary result = transactionConfirmationService.getTransactionSummaryService(1);

        assertEquals(2, result.getCompletedTransactions().size());
        assertEquals(1, result.getIncompleteTransactions().size());
        assertTrue(result.getCompletedTransactions().contains(5));
        assertTrue(result.getCompletedTransactions().contains(15));
    }
    
 // Test for retrieving a summary for a user with no completed transactions.
    @Test
    public void testGetTransactionSummaryServiceNoCompletedTransactions() {

        TransactionSummary result = transactionConfirmationService.getTransactionSummaryService(4);

        assertTrue(result.getCompletedTransactions().isEmpty());
    }


    // Test for checking if the user has received the notification.
    @Test
    public void testHasReceivedNotificationServiceSuccess() {

        boolean result = transactionConfirmationService.hasReceivedNotificationService(1,15);

        assertTrue(result);
    }

    // Test for checking if the user has not received the notification.
    @Test
    public void testHasReceivedNotificationServiceFailure() {
        Integer userId = 1;
        Integer transactionId = 5;

        boolean result = transactionConfirmationService.hasReceivedNotificationService(1,5);

        assertFalse(result);
    }

}
