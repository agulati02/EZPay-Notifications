package com.ezpay;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.ezpay.entity.Notification;
import com.ezpay.entity.Transaction;
import com.ezpay.entity.User;
import com.ezpay.repository.NotificationRepository;
import com.ezpay.repository.TransactionRepository;
import com.ezpay.repository.UserRepository;
import com.ezpay.service.TransactionConfirmationService;

/**
 * Test class for the NotificationService.
 * This class contains unit tests to verify the functionality of the NotificationService.
 * No external module used
 * 
 * @author Geethapriya Thandavamurthi
 * @date 2024-09-02
 */


@SpringBootTest
class TransactionConfirmationServiceTests {


    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private TransactionRepository transactionRepository;

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
        public NotificationRepository notificationRepository() {
            return mock(NotificationRepository.class);
        }

        @Bean
        public UserRepository userRepository() {
            return mock(UserRepository.class);
        }

        @Bean
        public TransactionRepository transactionRepository() {
            return mock(TransactionRepository.class);
        }
        
        @Bean
        public JavaMailSender getJavaMailSender() {
            JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
            
            // Setting mail server properties
            mailSender.setHost("smtp.gmail.com");
            mailSender.setPort(587);
            mailSender.setUsername("ezpay1712@gmail.com");
            mailSender.setPassword("imhl ehax mvmr trbg");

            // Configuring JavaMail properties
            Properties props = mailSender.getJavaMailProperties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");  // Optional: Enables debug logs for mail sending

            return mailSender;
        }
    }
    
    @Before
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processTransactionUserExistsAndNotificationsEnabled() {
        //A completed Transaction with id = 1L with userId = U01 is created
        Transaction transaction = new Transaction();
        transaction.setId(1L);
        transaction.setUserId("U01");
        transaction.setStatus("COMPLETED");
        transaction.setDateOfTransaction(new Date());

        //An User with id = U01 and notification enabled is created
        User user = new User();
        user.setId("U01");
        user.setNotificationsEnabled(true);
        
        //User with id = U01 found so return user 
        when(userRepository.findUserById("U01")).thenReturn(user);
       
        //process the transaction
        transactionConfirmationService.processTransaction(transaction);
     
        //check if save method of transactionRepository is called exactly once
        verify(transactionRepository, times(1)).save(transaction);
        //check if save method of notificationRepository is called exactly once
        verify(notificationRepository, times(1)).save(any(Notification.class));
    }

    @Test
    void processTransactionUserNotFound() {
        //Transaction with userId = U02 is created
        Transaction transaction = new Transaction();
        transaction.setUserId("U02");
        
        //User with id = U02 does not exist so return null
        when(userRepository.findUserById("U02")).thenReturn(null);
        
        //process the transaction
        transactionConfirmationService.processTransaction(transaction);

        //check if save method of transactionRepository is never called 
        verify(transactionRepository, never()).save(transaction);
        //check if save method of notificationRepository is never called 
        verify(notificationRepository, never()).save(any(Notification.class));
    }

    @Test
    void getNotificationsForUserUserExistsAndNotificationsEnabled() {
    	//An User with id = U01 and notification enabled is created
        User user = new User();
        user.setId("U01");
        user.setNotificationsEnabled(true);
        
        //Notification with notificationContent = Notification 1, userId = U01, and transactionId = 1L is created
        Notification notification1 = new Notification("Notification 1", "U01", 1L);
        
        //Notification with notificationContent = Notification 2, userId = U01, and transactionId = 2L is created
        Notification notification2 = new Notification("Notification 2", "U01", 2L);
        
        List<Notification> notifications = Arrays.asList(notification1, notification2);
        
        //User with id = U01 found so return user 
        when(userRepository.findUserById("U01")).thenReturn(user);
        //User with id = U01 found so return corresponding notification
        when(notificationRepository.findByUserId("U01")).thenReturn(notifications);

        //Get notifications of User with id = U01 
        List<Notification> result = transactionConfirmationService.getNotificationsForUser("U01");

        //check if the number of retrieved notification = 2
        assertEquals(2, result.size());
        //check if findByUserId method of notificationRepository is called exactly once
        verify(notificationRepository, times(1)).findByUserId("U01");
    }

    @Test
    void getNotificationsForUserUserNotFound() {
    	//User with id = U03 does not exist so return null
        when(userRepository.findUserById("U03")).thenReturn(null);
        
      	//Get notifications of User with id = U03 
        List<Notification> result = transactionConfirmationService.getNotificationsForUser("U03");

        //check if the number of retrieved notification = 0
        assertNull(result);
        //check if findByUserId method of notificationRepository is never called
        verify(notificationRepository, never()).findByUserId("U03");
    }
}
