package com.ezpay.repository;

import com.ezpay.entity.PaymentReminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * PaymentReminderRepository interface
 * Repository interface for PaymentReminder entity, providing CRUD operations.
 * This interface allows for interaction with the database for
 * PaymentReminder-related queries.
 * 
 * Author: Doneela Das
 * Date: 02-09-2024
 */
@Repository
public interface PaymentReminderRepository extends JpaRepository<PaymentReminder, String> {
	/**
	 * Adds a new payment reminder.
	 * 
	 * @param reminderId the unique reminder ID.
	 * @param userId     the user ID associated with the reminder.
	 * @param amount     the amount to be reminded about.
	 * @param dueDate    the due date of the payment.
	 * @param status     the status of the reminder.
	 * @return the created PaymentReminder object.
	 */
	default boolean addPaymentReminder(String reminderId, String userId, Double amount, Date dueDate, String status) {
		PaymentReminder reminder = new PaymentReminder(reminderId, userId, amount, dueDate, status);
		return save(reminder) != null;
	}

	/**
	 * Deletes a payment reminder by reminder ID.
	 * 
	 * @param reminderId the unique reminder ID.
	 * @return true if the reminder was deleted, false otherwise.
	 */
	default boolean deletePaymentReminder(String reminderId) {
		if (existsById(reminderId)) {
			deleteById(reminderId);
			return true;
		}
		return false;
	}

	/**
	 * Deletes all payment reminders for a specific user.
	 * 
	 * @param userId the unique user ID.
	 * @return the number of reminders deleted.
	 */
	@Transactional
	@Modifying
	@Query("DELETE FROM PaymentReminder pr WHERE pr.userId = :userId")
	int deleteAllByUserId(@Param("userId") String userId);

	/**
	 * Fetches payment reminders for a specific user and returns the ones that are
	 * due within the
	 * next 3 days.
	 * 
	 * @param userId the unique user ID.
	 * @return a list of PaymentReminder objects.
	 */
	@Query("SELECT pr FROM PaymentReminder pr WHERE pr.userId = :userId")
	default List<PaymentReminder> findRemindersByUserId(@Param("userId") String userId) {
		List<PaymentReminder> reminderList = findByUserId(userId);
		Iterator<PaymentReminder> iterator = reminderList.iterator();
		while (iterator.hasNext()) {
			Long differenceMilliseconds = iterator.next().getDueDate().getTime() - (new Date()).getTime();
			Long differenceDays = TimeUnit.MILLISECONDS.toDays(differenceMilliseconds) + 1;
			if (differenceDays >= 3) {
				iterator.remove();
			}
		}
		return reminderList;
	}

	/**
	 * Fetches all payment reminders for a specific user.
	 * 
	 * @param userId the unique user ID.
	 * @return a list of PaymentReminder objects.
	 */
	List<PaymentReminder> findByUserId(String userId);

	/**
     * Finds payment reminders that are due within 3 days or on the current date.
     * 
     * @param currentDate The current date for comparison.
     * @return List of reminders due within the next 3 days.
     */
    @Query("FROM PaymentReminder WHERE due_date BETWEEN :currentDate AND :currentDate + 3")
    List<PaymentReminder> findDueReminders(@Param("currentDate") Date currentDate);

}
