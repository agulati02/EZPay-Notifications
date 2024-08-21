package com.ezpay.notifications.service;

// Import Packages and Libraries to run the Test Cases
import com.ezpay.notifications.model.TransactionConfirmation;
import com.ezpay.notifications.model.TransactionSummary;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TransactionConfirmationServiceTest {

    // Fields to hold the instance of the service being tested, the input, and the expected outputs for each test
    private TransactionConfirmationService transactionConfirmationService;
    private TransactionConfirmation transactionConfirmation;
    private boolean expectedCompletion;
    private String expectedMessage;
    private Integer expectedNumCompleted;
    private String expectedNotificationStatus;
    private int expectedCompletedCount;
    private int expectedIncompleteCount;

    /**
      Constructor to initialize the parameters for each test case.
     * @param transactionConfirmation    Input TransactionConfirmation object.
     * @param expectedCompletion         Expected result indicating whether the transaction was completed.
     * @param expectedMessage            Expected confirmation message.
     * @param expectedNumCompleted       Expected number of completed transactions.
     * @param expectedNotificationStatus Expected notification status for the transaction.
     * @param expectedCompletedCount     Expected count of completed transactions in the summary.
     * @param expectedIncompleteCount    Expected count of incomplete transactions in the summary.
     */
    public TransactionConfirmationServiceTest(TransactionConfirmation transactionConfirmation, 
                                               boolean expectedCompletion, 
                                               String expectedMessage,
                                               Integer expectedNumCompleted,
                                               String expectedNotificationStatus,
                                               int expectedCompletedCount,
                                               int expectedIncompleteCount) {
        this.transactionConfirmation = transactionConfirmation;
        this.expectedCompletion = expectedCompletion;
        this.expectedMessage = expectedMessage;
        this.expectedNumCompleted = expectedNumCompleted;
        this.expectedNotificationStatus = expectedNotificationStatus;
        this.expectedCompletedCount = expectedCompletedCount;
        this.expectedIncompleteCount = expectedIncompleteCount;
    }

    /**
     ** Provides a set of test parameters to be used by the Parameterized runner.
     ** Each array represents a set of parameters for one test case.
     * 
     * @return A collection of test cases, each containing input and expected output.
     */
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
            // Test Case 1: Valid transaction and user where the transaction is completed
            {new TransactionConfirmation(1, 5), true, "Transaction Completed", 3, "Not received the Notification", 3, 2},
            // Test Case 2: Valid transaction and user with a different transaction ID but completed
            {new TransactionConfirmation(1, 10), true, "Transaction Completed", 3, "Not received the Notification", 3, 2},
            // Test Case 3: Valid user and transaction but incomplete
            {new TransactionConfirmation(1, 2), false, "Transaction Incompleted", 3, "Not received the Notification", 3, 2},
            // Test Case 4: Valid user with an invalid transaction ID
            {new TransactionConfirmation(1, -1), false, "Transaction not performed by User", 3, "Not received the Notification", 3, 2},
            // Test Case 5: Both user and transaction ID are invalid
            {new TransactionConfirmation(-1, -1), false, "Transaction not performed by User", 0, "Not received the Notification", 0, 0},
            // Test Case 6: User has enabled notifications
            {new TransactionConfirmation(3), false, "Transaction not performed by User", 0, "Not received the Notification", 0, 1},
            // Test Case 7: User has disabled notifications
            {new TransactionConfirmation(1), false, "Transaction not performed by User", 3, "Not received the Notification", 3, 2},
            // Test Case 8: Null TransactionConfirmation object
            {null, false, null, null, null, 0, 0},
            // Test Case 9: TransactionConfirmation object with null ID
            {new TransactionConfirmation(null), false, "Transaction not performed by User", 0, "Not received the Notification", 0, 0},
            // Test Case 10: User ID does not exist in the system
            {new TransactionConfirmation(999), false, "Transaction not performed by User", 0, "Not received the Notification", 0, 0}
        });
    }

    /**
     ** This method is executed before each test case. 
     ** It initializes the TransactionConfirmationService instance.
     */
    @Before
    public void setUp() {
        transactionConfirmationService = new TransactionConfirmationService();
    }

    /**
     ** This method is executed after each test case. 
     ** It clears the service instance and transaction confirmation object.
     */
    @After
    public void tearDown() {
        transactionConfirmationService = null;
        transactionConfirmation = null;
    }

    /**
     ** Test case for the hasCompletedTransactionService method.
     ** It checks whether the transaction was completed as expected.
     */
    @Test
    public void testHasCompletedTransactionService() {
        if (transactionConfirmation != null) {
            TransactionConfirmation result = transactionConfirmationService
                    .hasCompletedTransactionService(transactionConfirmation);
            if (expectedCompletion) {
                assertNotNull(result);
                assertTrue(result.getHasCompleted());
            } else {
                assertNull(result);
            }
        } else {
            // Handle null case if needed
            assertNull(transactionConfirmationService
                    .hasCompletedTransactionService(transactionConfirmation));
        }
    }

    /**
     ** Test case for the hasEnabledNotificationService method.
     ** It checks whether the user has enabled notifications as expected.
     */
    @Test
    public void testHasEnabledNotificationService() {
        if (transactionConfirmation != null) {
            TransactionConfirmation result = transactionConfirmationService
                    .hasEnabledNotitificationService(transactionConfirmation);
            if (result != null) {
                assertTrue(result.getEnabledNotification());
            } else {
                assertNull(result);
            }
        } else {
            assertNull(transactionConfirmationService
                    .hasEnabledNotitificationService(transactionConfirmation));
        }
    }

    /**
     ** Test case for the getConfirmationMessageService method.
     ** It checks whether the confirmation message is as expected.
     */
    @Test
    public void testGetConfirmationMessageService() {
        if (transactionConfirmation != null) {
            String message = transactionConfirmationService.getConfirmationMessageService(transactionConfirmation);
            assertEquals(expectedMessage, message);
        } else {
            assertNull(transactionConfirmationService.getConfirmationMessageService(transactionConfirmation));
        }
    }

    /**
     ** Test case for the numCompletedTransactionService method.
     ** It checks whether the number of completed transactions is as expected.
     */
    @Test
    public void testNumCompletedTransactionService() {
        if (transactionConfirmation != null) {
            Integer result = transactionConfirmationService.numCompletedTransactionService(transactionConfirmation);
            assertEquals(expectedNumCompleted, result);
        } else {
            assertNull(transactionConfirmationService.numCompletedTransactionService(transactionConfirmation));
        }
    }

    /**
     ** Test case for the getTransactionSummaryService method.
     ** It checks whether the transaction summary (completed and incomplete counts) is as expected.
     */
    @Test
    public void testGetTransactionSummaryService() {
        if (transactionConfirmation != null) {
            TransactionSummary result = transactionConfirmationService
                    .getTransactionSummaryService(transactionConfirmation);
            assertEquals(expectedCompletedCount, result.getCompletedTransactions().size());
            assertEquals(expectedIncompleteCount, result.getIncompleteTransactions().size());
        } else {
            assertNull(transactionConfirmationService.getTransactionSummaryService(transactionConfirmation));
        }
    }

    /**
     ** Test case for the hasReceivedNotificationService method.
     ** It checks whether the notification status is as expected.
     */
    @Test
    public void testHasReceivedNotificationService() {
        if (transactionConfirmation != null) {
            String notificationStatus = transactionConfirmationService
                    .hasReceivedNotificationService(transactionConfirmation);
            assertEquals(expectedNotificationStatus, notificationStatus);
        } else {
            assertNull(transactionConfirmationService
                    .hasReceivedNotificationService(transactionConfirmation));
        }
    }
}
