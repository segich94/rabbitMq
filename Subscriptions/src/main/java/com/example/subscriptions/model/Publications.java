package com.example.subscriptions.model;

import lombok.Data;

import java.util.List;
@Data
public class Publications {
    private String title;
    private List<String> users;
}
