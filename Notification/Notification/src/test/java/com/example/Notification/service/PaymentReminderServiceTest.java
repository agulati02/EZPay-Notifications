package com.example.Notification.service;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * This module is to test the payment reminder system from its service layer.
 * Parameterized tests are generated and tested for each method.
 * 
 * Author: Anurag Gulati Date: 21.08.2024
 */

@SpringBootTest
@RunWith(Parameterized.class)
public class PaymentReminderServiceTest {

	private PaymentReminderService paymentReminderService;

	// Parameters for the test cases
	private int testId;

	private String reminderId;
	private String userId;
	private Double amount;
	private Date dueDate;
	private String status;

	private boolean expectedResult;
	private int expectedDeleteCount;
	private int expectedFetchSize;

	public PaymentReminderServiceTest(int testId, String reminderId, String userId, Double amount, Date dueDate,
			String status, boolean expectedResult, int expectedDeleteCount, int expectedFetchSize) {
		this.reminderId = reminderId;
		this.userId = userId;
		this.amount = amount;
		this.dueDate = dueDate;
		this.status = status;

		this.testId = testId;
		this.expectedResult = expectedResult;
		this.expectedDeleteCount = expectedDeleteCount;
		this.expectedFetchSize = expectedFetchSize;
	}

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		paymentReminderService = new PaymentReminderService();
	}

	@Parameterized.Parameters
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][] {
				/* Test case for adding payment reminders */
				// CASE 1: Valid addition
				{ 1, "R01U06", "U06", 500.00, new Date(), "Pending", true, 0, 3 },
				// CASE 2: Invalid addition (reminderId is null)
				{ 1, null, "U06", 100.00, new Date(), "Pending", false, 0, 0 },
				// CASE 3: Invalid addition (userId is null)
				{ 1, "R01U06", null, 100.00, new Date(), "Pending", false, 0, 0 },
				// CASE 4: Invalid addition (reminderId and userId are null)
				{ 1, null, null, 100.00, new Date(), "Pending", false, 0, 0 },

				/* Test case for deleting payment reminder */
				// CASE 1: Valid delete by reminder ID
				{ 2, "R01U01", "U01", 0.0, null, null, true, 1, 0 },
				// CASE 2: Invalid delete (non-existing reminderID)
				{ 2, "R100U01", "U01", 0.0, null, null, false, 0, 0 },
				// CASE 3: Invalid delete (null reminderID)
				{ 2, null, "U01", 0.0, null, null, false, 0, 0 },

				/* Test case for deleting all reminders for a user */
				// CASE 1: Delete all reminders for U03
				{ 3, null, "U03", 0.0, null, null, true, 4, 0 },
				// CASE 2: Delete all for non-existing user ID
				{ 3, null, "U300", 0.0, null, null, false, 0, 0 },
				// CASE 3: Delete all for null user ID
				{ 3, null, null, 0.0, null, null, false, 0, 0 },

				/* Test case for fetching payment reminders */
				// CASE 1: Fetch all reminders for U04
				{ 4, null, "U04", 0.0, null, null, true, 0, 3 },
				// CASE 2: Fetch reminders for non-existing user ID
				{ 4, null, "U400", 0.0, null, null, false, 0, 0 },
				// CASE 3: Fetch reminders for null user ID
				{ 4, null, null, 0.0, null, null, false, 0, 0 } });
	}

	@Test
	public void testAddPaymentReminderService() throws SQLException {
		Assume.assumeTrue(testId == 1);

		boolean result = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status);
		assertEquals(expectedResult, result);
	}

	@Test
	public void testDeletePaymentReminderService() throws SQLException {
		Assume.assumeTrue(testId == 2);

		boolean result = paymentReminderService.deletePaymentReminderService(reminderId);
		assertEquals(expectedResult, result);
	}

	@Test
	public void testDeleteAllUserRemindersService() throws SQLException {
		Assume.assumeTrue(testId == 3);

		int result = paymentReminderService.deleteAllUserRemindersService(userId);
		assertEquals(expectedDeleteCount, result);
	}

	@Test
	public void testFetchPaymentRemindersService() {
		Assume.assumeTrue(testId == 4);

		int result = paymentReminderService.fetchPaymentRemindersService(userId).size();
		assertEquals(expectedFetchSize, result);
	}
}
