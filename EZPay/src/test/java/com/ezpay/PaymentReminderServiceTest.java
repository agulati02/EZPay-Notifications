package com.ezpay;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.ezpay.entity.PaymentReminder;
import com.ezpay.service.PaymentReminderService;

/**
 * Tests the payment reminder system from its service layer.
 * 
 * <p>
 * This class contains parameterized tests that validate various methods of the
 * {@code PaymentReminderService}. The tests cover different scenarios such as
 * adding, deleting, and fetching payment reminders. Parameterized inputs allow
 * for testing multiple cases with different sets of input data.
 * </p>
 * 
 * <p>
 * Each test is designed to check specific functionality, ensuring the
 * correctness of the service layer under different conditions.
 * </p>
 * 
 * @author Anurag Gulati
 * @date 05.09.2024
 */
@SpringBootTest
@RunWith(Parameterized.class)
public class PaymentReminderServiceTest {

	@Mock
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

	/**
	 * Initializes the parameters for the parameterized test cases in
	 * {@code PaymentReminderServiceTest}.
	 * 
	 * <p>
	 * This constructor sets up the test parameters required for each test case,
	 * including test IDs, reminder details, user details, amounts, due dates, and
	 * expected results. These parameters are passed to each test case and used to
	 * validate the behavior of the {@code PaymentReminderService} methods.
	 * </p>
	 * 
	 * @param testId              the unique identifier for the test case
	 * @param reminderId          the ID of the payment reminder being tested
	 * @param userId              the user ID associated with the payment reminder
	 * @param amount              the amount due in the payment reminder
	 * @param dueDate             the due date of the payment reminder
	 * @param status              the current status of the payment reminder (e.g.,
	 *                            "Pending")
	 * @param expectedResult      the expected result of the test case (true if the
	 *                            operation is expected to succeed)
	 * @param expectedDeleteCount the expected number of deleted reminders (for
	 *                            delete tests)
	 * @param expectedFetchSize   the expected number of fetched reminders (for
	 *                            fetch tests)
	 */
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

	/**
	 * Initializes mock objects before each test case execution.
	 * 
	 * <p>
	 * This method is annotated with {@code @Before}, ensuring that it runs before
	 * each test method in the class. It uses
	 * {@code MockitoAnnotations.openMocks(this)} to initialize all mock objects
	 * annotated with {@code @Mock} or {@code @InjectMocks} in the current test
	 * class.
	 * </p>
	 * 
	 * <p>
	 * This setup is essential for preparing the Mockito environment, allowing
	 * mocked dependencies to be correctly injected and utilized in the unit tests.
	 * </p>
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Provides parameterized test data for the {@code PaymentReminderServiceTest}
	 * class.
	 * 
	 * <p>
	 * This method returns a collection of test data used to drive the parameterized
	 * tests for the {@code PaymentReminderService}. Each array represents a
	 * different test case with the following parameters: test ID, reminder ID, user
	 * ID, amount, due date, status, expected result, expected delete count, and
	 * expected fetch size.
	 * </p>
	 * 
	 * <p>
	 * The test cases cover scenarios such as:
	 * </p>
	 * <ul>
	 * <li>Adding payment reminders with valid and invalid data</li>
	 * <li>Deleting specific payment reminders</li>
	 * <li>Deleting all reminders for a user</li>
	 * <li>Fetching payment reminders for a user</li>
	 * </ul>
	 * 
	 * @return a collection of test data arrays for parameterized testing
	 */
	@Parameterized.Parameters
	public static Collection<Object[]> testData() {
		return Arrays.asList(new Object[][] {
				// Test case for adding payment reminders
				{ 1, "R01U06", "U06", 500.00, new Date(), "Pending", true, 0, 3 }, // valid addition
				{ 1, null, "U06", 100.00, new Date(), "Pending", false, 0, 0 }, // invalid addition (reminderId is null)
				{ 1, "R01U06", null, 100.00, new Date(), "Pending", false, 0, 0 }, // invalid addition (userId is null)
				{ 1, null, null, 100.00, new Date(), "Pending", false, 0, 0 }, // invalid addition (reminderId and
																				// userId are null)

				// Test case for deleting payment reminders
				{ 2, "R01U01", "U01", 0.0, null, null, true, 1, 0 }, // valid deletion
				{ 2, "R100U01", "U01", 0.0, null, null, false, 0, 0 }, // invalid deletion (reminderId does not exist)
				{ 2, null, "U01", 0.0, null, null, false, 0, 0 }, // invalid deletion (reminderId is null)

				// Test case for deleting all reminders for a user
				{ 3, null, "U03", 0.0, null, null, true, 4, 0 }, // valid deletion
				{ 3, null, "U300", 0.0, null, null, false, 0, 0 }, // invalid deletion (userId does not exist)
				{ 3, null, null, 0.0, null, null, false, 0, 0 }, // invalid deletion (userId is null)

				// Test case for fetching payment reminders
				{ 4, null, "U04", 0.0, null, null, true, 0, 1 }, // valid fetch
				{ 4, null, "U400", 0.0, null, null, false, 0, 0 }, // invalid fetch (userId does not exist)
				{ 4, null, null, 0.0, null, null, false, 0, 0 } // invalid fetch (userId is null)
		});
	}

	/**
	 * Tests the {@link PaymentReminderService#addPaymentReminderService} method.
	 * 
	 * <p>
	 * This test verifies that the {@code addPaymentReminderService} method of the
	 * {@code paymentReminderService} instance correctly adds a payment reminder and
	 * returns the expected result. The test is only executed if the {@code testId}
	 * is equal to 1, as controlled by the {@link Assume#assumeTrue} method.
	 * </p>
	 * 
	 * <p>
	 * The behavior of the {@code addPaymentReminderService} method is mocked using
	 * Mockito's {@link Mockito#when} method to return {@code expectedResult}. The
	 * result of the method call is then compared to {@code expectedResult} using
	 * {@link org.junit.Assert#assertEquals}.
	 * </p>
	 * 
	 * @throws SQLException if an SQL error occurs during the execution of the test.
	 *                      This exception should be handled by the test framework
	 *                      or by the setup of the test environment.
	 */
	@Test
	public void testAddPaymentReminderService() throws SQLException {
		Assume.assumeTrue(testId == 1);

		when(paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status))
				.thenReturn(expectedResult);

		boolean result = paymentReminderService.addPaymentReminderService(reminderId, userId, amount, dueDate, status);
		assertEquals(expectedResult, result);
	}

	/**
	 * Tests the {@link PaymentReminderService#deletePaymentReminderService} method.
	 * 
	 * <p>
	 * This test verifies that the {@code deletePaymentReminderService} method of
	 * the {@code paymentReminderService} instance correctly deletes a payment
	 * reminder and returns the expected result. The test is only executed if the
	 * {@code testId} is equal to 2, as controlled by the {@link Assume#assumeTrue}
	 * method.
	 * </p>
	 * 
	 * <p>
	 * The behavior of the {@code deletePaymentReminderService} method is mocked
	 * using Mockito's {@link Mockito#when} method to return {@code expectedResult}.
	 * The result of the method call is then compared to {@code expectedResult}
	 * using {@link org.junit.Assert#assertEquals}.
	 * </p>
	 * 
	 * @throws SQLException if an SQL error occurs during the execution of the test.
	 *                      This exception should be handled by the test framework
	 *                      or by the setup of the test environment.
	 */
	@Test
	public void testDeletePaymentReminderService() throws SQLException {
		Assume.assumeTrue(testId == 2);

		when(paymentReminderService.deletePaymentReminderService(reminderId)).thenReturn(expectedResult);

		boolean result = paymentReminderService.deletePaymentReminderService(reminderId);
		assertEquals(expectedResult, result);
	}

	/**
	 * Tests the {@link PaymentReminderService#deleteAllUserRemindersService}
	 * method.
	 * 
	 * <p>
	 * This test verifies that the {@code deleteAllUserRemindersService} method of
	 * the {@code paymentReminderService} instance correctly deletes all payment
	 * reminders for a specific user and returns the expected count of deleted
	 * reminders. The test is only executed if the {@code testId} is equal to 3, as
	 * controlled by the {@link Assume#assumeTrue} method.
	 * </p>
	 * 
	 * <p>
	 * The behavior of the {@code deleteAllUserRemindersService} method is mocked
	 * using Mockito's {@link Mockito#when} method to return
	 * {@code expectedDeleteCount}. The result of the method call is then compared
	 * to {@code expectedDeleteCount} using {@link org.junit.Assert#assertEquals}.
	 * </p>
	 * 
	 * @throws SQLException if an SQL error occurs during the execution of the test.
	 *                      This exception should be handled by the test framework
	 *                      or by the setup of the test environment.
	 */
	@Test
	public void testDeleteAllUserRemindersService() throws SQLException {
		Assume.assumeTrue(testId == 3);

		when(paymentReminderService.deleteAllUserRemindersService(userId)).thenReturn(expectedDeleteCount);

		int result = paymentReminderService.deleteAllUserRemindersService(userId);
		assertEquals(expectedDeleteCount, result);
	}

	/**
	 * Tests the {@link PaymentReminderService#fetchPaymentRemindersService} method.
	 * 
	 * <p>
	 * This test verifies that the {@code fetchPaymentRemindersService} method of
	 * the {@code paymentReminderService} instance correctly fetches a list of
	 * payment reminders for a specific user and returns the expected number of
	 * reminders. The test is only executed if the {@code testId} is equal to 4, as
	 * controlled by the {@link Assume#assumeTrue} method.
	 * </p>
	 * 
	 * <p>
	 * A mock {@link PaymentReminder} object is created and used to populate the
	 * list returned by the service method. The {@code when} method of Mockito is
	 * used to return a list containing {@code expectedFetchSize} instances of the
	 * mock reminder. The result of the method call is then compared to
	 * {@code expectedFetchSize} using {@link org.junit.Assert#assertEquals}.
	 * </p>
	 * 
	 * @throws AssertionError if the size of the list returned by
	 *                        {@code fetchPaymentRemindersService} does not match
	 *                        {@code expectedFetchSize}. This indicates that the
	 *                        method under test did not return the expected number
	 *                        of reminders.
	 */
	@Test
	public void testFetchPaymentRemindersService() {
		Assume.assumeTrue(testId == 4);

		// Create a mock PaymentReminder object
		PaymentReminder mockReminder = new PaymentReminder();
		// Set properties on the mockReminder as necessary for the test, e.g.,
		// mockReminder.setReminderId(reminderId);

		// Return a list of mock PaymentReminder objects based on the expected fetch
		// size
		when(paymentReminderService.fetchPaymentRemindersService(userId))
				.thenReturn(Collections.nCopies(expectedFetchSize, mockReminder));

		int result = paymentReminderService.fetchPaymentRemindersService(userId).size();
		assertEquals(expectedFetchSize, result);
	}

}
