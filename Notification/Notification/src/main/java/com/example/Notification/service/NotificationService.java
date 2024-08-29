package com.example.Notification.service;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.model.User;
import com.example.Notification.repository.NotificationRepo;
import com.example.Notification.repository.TransactionRepo;
import com.example.Notification.repository.UserRepo;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    @Autowired
    NotificationRepo notificationRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TransactionRepo transactionRepo;


    public void processTransation(Transaction transaction) {
        //find user from UID
        Long uid = transaction.getUserId();
        System.out.println("UID: "+ uid);
        User user = userRepo.findUserById(uid);
        if(user != null) {

            //add transaction to the db (process transaction)
            try {
                transactionRepo.save(transaction);
            }catch (Exception e){
                System.out.println("Exception occured while adding Transaction!! "+e);
            }


            if(user.isNotificationEnabled())
                //add new notification
                notificationRepo.save(new Notification("Transaction "+transaction.getStatus()+" Transaction ID: "+ transaction.getId(), uid, transaction.getId()));
        }else {
            System.out.println("User Not Found!");
        }
    }


    public List<Notification> getNotificationsForUser(Long uId){
        //find user from UID

        User user = userRepo.findUserById(uId);
        if(user != null) {
            if(user.isNotificationEnabled()) {
                return notificationRepo.findByUserId(uId);
            }else {
                System.out.println("Notification Disabled for this user");
                return null;
            }
        }else {
            System.out.println("User Not Found!");
        }

        return null;
    }
}
