package com.example.Notification.controller;

import com.example.Notification.model.Notification;
import com.example.Notification.model.Transaction;
import com.example.Notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    NotificationService notificationService;

    @PostMapping("/processTransaction/")
    public void processTransaction(@RequestBody Transaction transaction){
        System.out.println(transaction.getId() + "---------" + transaction.getUserId());
        notificationService.processTransation(transaction);
        return;
    }
    @GetMapping("/notifications/{uid}")
    public List<Notification> getUserNotification(@PathVariable Long uid){
        return notificationService.getNotificationsForUser(uid);
    }
}
