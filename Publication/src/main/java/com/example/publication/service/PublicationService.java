package com.example.publication.service;

import com.example.publication.model.Publication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class PublicationService {
    private final RabbitTemplate rabbitTemplate;

    public Publication createPublication(Publication publication) {
        Publication newPublication = Publication.builder()
                .author(publication.getAuthor())
                .body(publication.getBody())
                .title(publication.getTitle())
                .build();
        sendMessage(publication.getTitle(), "testRoutingKey");
        return newPublication;
    }
    private void sendMessage(String title, String routingKey){
        rabbitTemplate.convertAndSend("new_publications", routingKey, title);
    }
}
