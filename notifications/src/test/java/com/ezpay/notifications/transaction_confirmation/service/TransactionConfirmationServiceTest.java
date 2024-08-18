
package com.ezpay.notifications.transaction_confirmation.service;

import com.ezpay.notifications.transaction_confirmation.model.TransactionConfirmation;
import com.ezpay.notifications.transaction_confirmation.model.TransactionSummary;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;

/*
 * Author Name: Akhil Kholia 
 * Date Details: 13/08/2024 
 */


public class TransactionConfirmationServiceTest {

	private TransactionConfirmationService transactionConfirmationService;
	private TransactionConfirmation transactionConfirmation;

	@Before
	public void setUp() {
		transactionConfirmationService = new TransactionConfirmationService();
		transactionConfirmation = new TransactionConfirmation(1, 5);
	}

	@After
	public void tearDown() {
		transactionConfirmationService = null;
		transactionConfirmation = null;
	}

	@Test
	public void testHasCompletedTransactionServiceSuccess() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1, 5);
		TransactionConfirmation result = transactionConfirmationService
				.hasCompletedTransactionService(transactionConfirmation);
		assertNotNull(result);
		assertTrue(result.getHasCompleted());
	}

	@Test
	public void testHasCompletedTransactionServiceFailure() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1, 2);
		TransactionConfirmation result = transactionConfirmationService
				.hasCompletedTransactionService(transactionConfirmation);
		assertNull(result);
	}

	@Test
	public void testHasEnabledNotificationServiceEnabled() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(3);
		TransactionConfirmation result = transactionConfirmationService
				.hasEnabledNotitificationService(transactionConfirmation);
		assertNotNull(result);
		assertTrue(result.getEnabledNotification());
	}

	@Test
	public void testHasEnabledNotificationServiceDisabled() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(999); // Invalid user ID
		TransactionConfirmation result = transactionConfirmationService
				.hasEnabledNotitificationService(transactionConfirmation);
		assertNull(result);
	}

	@Test
	public void testGetConfirmationMessageServiceSuccess() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1, 5);
		String message = transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction Completed", message);
	}

	@Test
	public void testGetConfirmationMessageServiceFailure() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1, 999); // Invalid transaction ID
		String message = transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction not performed by User", message);
	}

	@Test
	public void testNumCompletedTransactionServiceSuccess() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1);
		Integer result = transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertEquals(Integer.valueOf(2), result);
	}

	@Test
	public void testNumCompletedTransactionServiceFailure() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(3);
		Integer result = transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertEquals(Integer.valueOf(0), result);
	}

	@Test
	public void testGetTransactionSummaryServiceValidUser() {

		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1);
		TransactionSummary result = transactionConfirmationService
				.getTransactionSummaryService(transactionConfirmation);
		assertEquals(2, result.getCompletedTransactions().size());
		assertEquals(1, result.getIncompleteTransactions().size());
		assertTrue(result.getCompletedTransactions().contains(5));
		assertTrue(result.getCompletedTransactions().contains(15));
	}

	@Test
	public void testGetTransactionSummaryServiceInvalidUser() {
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(4); // no transactions for this
																							// user
		TransactionSummary result = transactionConfirmationService
				.getTransactionSummaryService(transactionConfirmation);
		assertTrue(result.getCompletedTransactions().isEmpty());
	}

	@Test
	public void testHasReceivedNotificationServiceSuccess() {
		transactionConfirmation.setTransactionId(15);
		String notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Received the Notification", notificationStatus);
	}

	@Test
	public void testHasReceivedNotificationServiceFailure() {
		transactionConfirmation.setTransactionId(5);
		String notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Not Received the Notification", notificationStatus);
	}
}
