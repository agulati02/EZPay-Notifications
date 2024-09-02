package com.ezpay.notifications.repo;

import com.ezpay.notifications.model.PaymentReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * PaymentReminderRepository interface
 * Repository interface for PaymentReminder entity, providing CRUD operations.
 * This interface allows for interaction with the database for PaymentReminder-related queries.
 * 
 * Author: Doneela Das
 * Date: 20.08.2024
 */
@Repository
public interface PaymentReminderRepository extends JpaRepository<PaymentReminder, String> {

    /**
     * Find all payment reminders for a user.
     *
     * @param userId the unique ID to identify the user.
     * @return a list of payment reminders corresponding to the given user.
     */
    List<PaymentReminder> findByUserId(String userId);

    /**
     * Delete all payment reminders for a user.
     *
     * @param userId the unique ID to identify the user.
     * @return the number of reminders deleted.
     */
    Integer deleteByUserId(String userId);

    /**
     * Find all payment reminders for a user with due date in the past or within the next 3 days.
     *
     * @param userId the unique ID to identify the user.
     * @param fromDate the start date for fetching reminders.
     * @param toDate the end date for fetching reminders.
     * @return a list of payment reminders.
     */
    List<PaymentReminder> findByUserIdAndDueDateBetween(String userId, Date fromDate, Date toDate);
}
