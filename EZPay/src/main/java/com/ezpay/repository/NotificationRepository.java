package com.ezpay.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ezpay.entity.Notification;

/**
 * NotificationRepo interface
 * Repository interface for Notification entity, providing CRUD operations.
 *  No external module used
 * 
 * @author Akhil Kholia
 * @date 2024-08-31
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

     /**
      * Finds notifications by user ID.
      * 
      * @param userId User ID to search notifications for.
      * @return List of Notification objects.
      */
     List<Notification> findByUserId(String uId);
}
