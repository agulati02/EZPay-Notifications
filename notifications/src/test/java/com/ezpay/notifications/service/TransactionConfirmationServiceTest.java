package com.ezpay.notifications.service;

import com.ezpay.notifications.model.TransactionConfirmation;
import com.ezpay.notifications.model.TransactionSummary;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;

/*
 ** Author Name: Jai Singh
 ** Date Details: 19/08/2024 
 */


/**
 ** Test class for the TransactionConfirmationService.
  This class contains unit tests to verify the functionality of the TransactionConfirmationService.
 */
public class TransactionConfirmationServiceTest {
	
	 // Instance of the service being tested
	private TransactionConfirmationService transactionConfirmationService;
    // Instance of the TransactionConfirmation object used in tests
	private TransactionConfirmation transactionConfirmation;
	
	 /**
     ** Sets up the test environment before each test case.
      Initializes the TransactionConfirmationService and creates a sample TransactionConfirmation object.
     */
	@Before
	public void setUp() {
		transactionConfirmationService = new TransactionConfirmationService();
		transactionConfirmation = new TransactionConfirmation(1, 5);
	}
	
	/**
     ** Cleans up the test environment after each test case.
     Sets the TransactionConfirmationService and TransactionConfirmation objects to null.
     */
	@After
	public void tearDown() {
		transactionConfirmationService = null;
		transactionConfirmation = null;
	}
	
	// testHasCompletedTransactionService: Used to Test the HasCompletedTransaction() Functionality( Complete Incomplete Both ) 
	@Test
	public void testHasCompletedTransactionService() {
		
		// Test Case 1 :Valid transaction and user where the transaction is completed
		TransactionConfirmation result = transactionConfirmationService
				.hasCompletedTransactionService(transactionConfirmation);
		assertNotNull(result);
		assertTrue(result.getHasCompleted());
		
	     // Test case 2: Valid transaction and user with a different transaction ID but completed
		TransactionConfirmation object2=new TransactionConfirmation(1,10);
		result=transactionConfirmationService
				.hasCompletedTransactionService(object2);
		assertNotNull(result);
		assertTrue(result.getHasCompleted());
		
		// Test Case 3: Valid User and Transaction but Incomplete 
		 transactionConfirmation = new TransactionConfirmation(1, 2);
		 result = transactionConfirmationService.hasCompletedTransactionService(transactionConfirmation);
		 assertNull(result);
		 
	   // Test Case 4: Valid User with Invalid Transaction Id
		 transactionConfirmation = new TransactionConfirmation(1,-1);
		 result=transactionConfirmationService.hasCompletedTransactionService(transactionConfirmation);
		 assertNull(result);
		 
	   // Test Case 5: Both User and Transaction Id are Invalid 
		 transactionConfirmation=new TransactionConfirmation(-1,-1);
		 result=transactionConfirmationService.hasCompletedTransactionService(transactionConfirmation);
		 assertNull(result);
	}
	
	// testHasEnabledNotificationService: Used to Test the HasEnabledNotification() Functionality of the Service( Sucess and Failure Both Cases )
	@Test
	public void testHasEnabledNotificationService() {
		
		// Test Case 1: When a User has Enabled the Notification 
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(3);
		TransactionConfirmation result = transactionConfirmationService
				.hasEnabledNotitificationService(transactionConfirmation);
		assertNotNull(result);
		assertTrue(result.getEnabledNotification());
		
		// Test Case 2: When a User has Disabled the Notification 
		transactionConfirmation=new TransactionConfirmation(1);
		result=transactionConfirmationService.hasEnabledNotitificationService(transactionConfirmation);
		assertNull(result);
		
		// Test Case 3: When the Transaction Confirmation Object is an null
		transactionConfirmation=null;
		result=transactionConfirmationService.hasEnabledNotitificationService(transactionConfirmation);
		assertNull(result);
		
		// Test Case 4: When the Transaction Confirmation Object has an null Id
		transactionConfirmation=new TransactionConfirmation(null);
		result=transactionConfirmationService.hasEnabledNotitificationService(transactionConfirmation);
		assertNull(result);
		
		// Test Case 5: When the User ID Does Not Exist in the System
		transactionConfirmation=new TransactionConfirmation(999);
		result=transactionConfirmationService.hasEnabledNotitificationService(transactionConfirmation);
		assertNull(result);	
	}
	
	// testGetConfirmationMessageService: Used to Test the GetConfirmationMessage() Functionality of the Service( Sucess and Failure Both Cases )
	@Test
	public void testGetConfirmationMessageService() {
		
		// Test Case 1:When an User Completed the Transaction 
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1, 5);
		String message = transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction Completed", message);
		
		// Test Case 2: When an User has not Completed the Transaction 
		transactionConfirmation=new TransactionConfirmation(1,3);
		message=transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction Incompleted",message);
		
		// Test Case 3: When an User has not Performed the Transaction
		transactionConfirmation=new TransactionConfirmation(1,20);
		message=transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction not performed by User",message);
		
		// Test Case 4: When an Null object is passed as an Object 
		transactionConfirmation=null;
		message=transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertNull(message);
		
		// Test Case 5: when User and Transaction Id both are Invalid
		transactionConfirmation=new TransactionConfirmation(-1,-1);
		message=transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
		assertEquals("Transaction not performed by User",message);
	}
	
	// testNumCompletedTransactionService: Used to Test the numCompletedTransaction() Functionality of the Services
	@Test
	public void testNumCompletedTransactionService() {
		
		// Test Case 1: When an User has Completed 'n' number of transaction 
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1);
		Integer result = transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertEquals(Integer.valueOf(3), result);
		
		// Test Case 2: When an User has not Completed any Transaction 
		transactionConfirmation=new TransactionConfirmation(3);
		result=transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertEquals(Integer.valueOf(0),result);
		
		// Test Case 3: When an null object is passes as an argument in the service function 
		transactionConfirmation=null;
		result=transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertNull(result);
		
		// Test Case 4: When User is Invalid 
		transactionConfirmation=new TransactionConfirmation(-1);
		result=transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
		assertEquals(Integer.valueOf(0),result);
	}
	
	// testGetTransactionSummaryService: used to test the getTransactionSummary() functionality of the Service
	@Test
	public void testGetTransactionSummaryService() {
		
		// Test Case 1: Transaction Summary of an Valid User 
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(1);
		TransactionSummary result = transactionConfirmationService
				.getTransactionSummaryService(transactionConfirmation);
		assertEquals(3, result.getCompletedTransactions().size());
		assertEquals(2, result.getIncompleteTransactions().size());
		assertTrue(result.getCompletedTransactions().contains(5));
		assertTrue(result.getCompletedTransactions().contains(15));
		
		// Test Case 2: User Perform more Completed Transaction than Incomplete 
		assertTrue(result.getCompletedTransactions().size()>result.getIncompleteTransactions().size());
		
		// Test Case 3: User Performs more Incomplete Transaction than Complete 
		transactionConfirmation=new TransactionConfirmation(3);
		result=transactionConfirmationService.getTransactionSummaryService(transactionConfirmation);
		assertTrue(result.getCompletedTransactions().size()<result.getIncompleteTransactions().size());
		
		// Test Case 4: when transactionConfirmation is null 
		transactionConfirmation=null;
		result=transactionConfirmationService.getTransactionSummaryService(transactionConfirmation);
		assertNull(result);
		
		// Test Case 5: When an User has Completed all the Transaction 
		transactionConfirmation=new TransactionConfirmation(2);
		result=transactionConfirmationService.getTransactionSummaryService(transactionConfirmation);
		assertEquals(0, result.getIncompleteTransactions().size());
	}
	
	// testHasReceivedNotificationService: Used to test the HasReceivedNotification() functionality of the Service Class
	@Test
	public void testHasReceivedNotificationService() {
		
		// Test Case 1: when the User has Received the Notification 
		TransactionConfirmation transactionConfirmation = new TransactionConfirmation(2,4);
		String notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Received the Notification", notificationStatus);
		
		// Test Case 2: When the user has not received the Notification 
		transactionConfirmation=new TransactionConfirmation(1,5);
		notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Not received the Notification", notificationStatus);
		
		// Test Case 3: User has Completed the Transaction but not Received the Notification 
		transactionConfirmation=new TransactionConfirmation(1,10);
		notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Not received the Notification", notificationStatus);
		
		// Test Case 4: User has not Completed the Transaction and Not Received the Notification 
		transactionConfirmation=new TransactionConfirmation(1,10);
		notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertEquals("Not received the Notification", notificationStatus);
		
		// Test Case 5: When an null object is passed as an argument
		transactionConfirmation=null;
		notificationStatus = transactionConfirmationService
				.hasReceivedNotificationService(transactionConfirmation);
		assertNull(notificationStatus);		
	}
}
