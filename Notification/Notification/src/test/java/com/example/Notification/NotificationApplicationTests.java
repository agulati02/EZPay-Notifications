package com.example.Notification;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.model.User;
import com.example.Notification.repository.NotificationRepo;
import com.example.Notification.repository.TransactionRepo;
import com.example.Notification.repository.UserRepo;
import com.example.Notification.service.TransactionConfirmationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Test class for the TransactionConfirmationService.
 * This class contains unit tests to verify the functionality of the TransactionConfirmationService.
 * 
 * @author Geethapriya Thandavamurthi
 * @date 2024-09-02
 */


@SpringBootTest
class NotificationApplicationTests {


    @Mock
    private NotificationRepo notificationRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private TransactionRepo transactionRepo;

    @InjectMocks
    private TransactionConfirmationService transactionConfirmationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessTransaction_UserExistsAndNotificationsEnabled() {
        //A completed Transaction with id = 1L with userId = 1L is created
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUserId(1L);
        transaction.setStatus("COMPLETED");

        //An User with id = 1L and notification enabled is created
        User user = new User();
        user.setId(1L);
        user.setNotificationsEnabled(true);

        when(userRepo.findUserById(1L)).thenReturn(user);
       
        transactionConfirmationService.processTransaction(transaction);
     
        verify(transactionRepo, times(1)).save(transaction);
        verify(notificationRepo, times(1)).save(any(Notification.class));
    }

    @Test
    void testProcessTransaction_UserNotFound() {
        //Transaction with userId = 2L is created
        Transaction transaction = new Transaction();
        transaction.setUserId(2L);
        
        //User with id = 2L does not exist
        when(userRepo.findUserById(2L)).thenReturn(null);
      
        transactionConfirmationService.processTransaction(transaction);

        verify(transactionRepo, never()).save(transaction);
        verify(notificationRepo, never()).save(any(Notification.class));
    }

    @Test
    void testGetNotificationsForUser_UserExistsAndNotificationsEnabled() {
    	//An User with id = 1L and notification enabled is created
        User user = new User();
        user.setId(1L);
        user.setNotificationsEnabled(true);
        
        //Notification with notificationContent = Notification 1, userId = 1L, and transactionId = 1L is created
        Notification notification1 = new Notification("Notification 1", 1L, 1L);
        
        //Notification with notificationContent = Notification 2, userId = 1L, and transactionId = 2L is created
        Notification notification2 = new Notification("Notification 2", 1L, 2L);
        
        List<Notification> notifications = Arrays.asList(notification1, notification2);

        when(userRepo.findUserById(1L)).thenReturn(user);
        when(notificationRepo.findByUserId(1L)).thenReturn(notifications);

        List<Notification> result = transactionConfirmationService.getNotificationsForUser(1L);

        assertEquals(2, result.size());
        verify(notificationRepo, times(1)).findByUserId(1L);
    }

    @Test
    void testGetNotificationsForUser_UserNotFound() {
    	//User with id = 3L does not exist
        when(userRepo.findUserById(3L)).thenReturn(null);

        List<Notification> result = transactionConfirmationService.getNotificationsForUser(3L);

        assertEquals(null, result);
        verify(notificationRepo, never()).findByUserId(3L);
    }
}
