package com.example.Notification.repository;

import com.example.Notification.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * NotificationRepo interface
 * Repository interface for Notification entity, providing CRUD operations.
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Repository
public interface NotificationRepo extends JpaRepository<Notification, Long> {
	
	/**
     * Finds notifications by user ID.
     * 
     * @param userId User ID to search notifications for.
     * @return List of Notification objects.
     */
     List<Notification> findByUserId(Long uId);
}
