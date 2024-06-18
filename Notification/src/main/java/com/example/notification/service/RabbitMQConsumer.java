package com.example.notification.service;

import com.example.notification.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RabbitMQConsumer {
    public void handleMessage(String message) {
        String[] strings = message.split("-");
        User user = new User();//get User from DB by username(in message) (strings[1])
        user.addNotification(strings[0]);
    }

    public List<String> getAllNotification(String username){
        User user = new User();//get User from DB by username
        return user.getNotification();
    }
}
