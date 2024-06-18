package com.example.subscriptions.service;

import com.example.subscriptions.model.Publications;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class RabbitMQConsumer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void handleMessage(String message){
        Publications publications = new Publications();//get Publications from DB by message
        for (String user : publications.getUsers()) {
            sendMessage(publications.getTitle() + "-" + user, "testRoutingKey");
        }
    }

    private void sendMessage(String title, String routingKey){
        rabbitTemplate.convertAndSend("notification_subscribe", routingKey, title);
    }
}
