package com.example.Notification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.model.User;
import com.example.Notification.repository.NotificationRepo;
import com.example.Notification.repository.TransactionRepo;
import com.example.Notification.repository.UserRepo;
import com.example.Notification.service.TransactionConfirmationService;

/**
 * Test class for the NotificationService.
 * This class contains unit tests to verify the functionality of the NotificationService.
 * No external module used
 * 
 * @author Geethapriya Thandavamurthi
 * @date 2024-09-02
 */


@SpringBootTest
public class NotificationApplicationTests {


    @Mock
    private NotificationRepo notificationRepository;

    @Mock
    private UserRepo userRepository;

    @Mock
    private TransactionRepo transactionRepository;

    @InjectMocks
    private TransactionConfirmationService transactionConfirmationService;

    // Static nested configuration class
    @Configuration
    static class TestConfig {

        // Define beans that are required for this test or mock them
        @Bean
        public TransactionConfirmationService transactionConfirmationService() {
            return new TransactionConfirmationService();
        }

        @Bean
        public NotificationRepo notificationRepo() {
            return mock(NotificationRepo.class);
        }

        @Bean
        public UserRepo userRepo() {
            return mock(UserRepo.class);
        }

        @Bean
        public TransactionRepo transactionRepo() {
            return mock(TransactionRepo.class);
        }
    }
    
    @Before
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testProcessTransactionUserExistsAndNotificationsEnabled() {
        //A completed Transaction with id = 1L with userId = 1L is created
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUserId(1L);
        transaction.setStatus("COMPLETED");

        //An User with id = 1L and notification enabled is created
        User user = new User();
        user.setId(1L);
        user.setNotificationsEnabled(true);
        
        //User with id = 1L found so return user 
        when(userRepository.findUserById(1L)).thenReturn(user);
       
        //process the transaction
        transactionConfirmationService.processTransaction(transaction);
     
        //check if save method of transactionRepository is called exactly once
        verify(transactionRepository, times(1)).save(transaction);
        //check if save method of notificationRepository is called exactly once
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void testProcessTransactionUserNotFound() {
        //Transaction with userId = 2L is created
        Transaction transaction = new Transaction();
        transaction.setUserId(2L);
        
        //User with id = 2L does not exist so return null
        when(userRepository.findUserById(2L)).thenReturn(null);
        
        //process the transaction
        transactionConfirmationService.processTransaction(transaction);

        //check if save method of transactionRepository is never called 
        verify(transactionRepository, never()).save(transaction);
        //check if save method of notificationRepository is never called 
        verify(notificationRepository, never()).save(any(Notification.class));
    }

    @Test
    void testGetNotificationsForUserUserExistsAndNotificationsEnabled() {
    	//An User with id = 1L and notification enabled is created
        User user = new User();
        user.setId(1L);
        user.setNotificationsEnabled(true);
        
        //Notification with notificationContent = Notification 1, userId = 1L, and transactionId = 1L is created
        Notification notification1 = new Notification("Notification 1", 1L, 1L);
        
        //Notification with notificationContent = Notification 2, userId = 1L, and transactionId = 2L is created
        Notification notification2 = new Notification("Notification 2", 1L, 2L);
        
        List<Notification> notifications = Arrays.asList(notification1, notification2);
        
        //User with id = 1L found so return user 
        when(userRepository.findUserById(1L)).thenReturn(user);
        //User with id = 1L found so return corresponding notification
        when(notificationRepository.findByUserId(1L)).thenReturn(notifications);

        //Get notifications of User with id = 1L 
        List<Notification> result = transactionConfirmationService.getNotificationsForUser(1L);

        //check if the number of retrieved notification = 2
        assertEquals(2, result.size());
        //check if findByUserId method of notificationRepository is called exactly once
        verify(notificationRepository, times(1)).findByUserId(1L);
    }

    @Test
    void testGetNotificationsForUserUserNotFound() {
    	//User with id = 3L does not exist so return null
        when(userRepository.findUserById(3L)).thenReturn(null);
        
      	//Get notifications of User with id = 3L 
        List<Notification> result = transactionConfirmationService.getNotificationsForUser(3L);

        //check if the number of retrieved notification = 0
        assertEquals(null, result);
        //check if findByUserId method of notificationRepository is never called
        verify(notificationRepository, never()).findByUserId(3L);
    }
}
