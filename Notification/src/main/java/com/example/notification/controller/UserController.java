package com.example.notification.controller;

import com.example.notification.service.RabbitMQConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
public class UserController {
    private final RabbitMQConsumer rabbitMQConsumer;
    public List<String> getAllNotifications(@RequestBody String username){
        return rabbitMQConsumer.getAllNotification(username);
    }
}
