package com.example.notification.model;

import lombok.Data;

import java.util.List;

@Data
public class User {
    private String username;
    private List<String> notification;

    public void addNotification(String notification){
        this.notification.add(notification);
    }
}
