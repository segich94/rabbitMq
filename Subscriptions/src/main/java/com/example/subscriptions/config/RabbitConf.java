package com.example.subscriptions.config;

import com.example.subscriptions.service.RabbitMQConsumer;
import org.springframework.amqp.core.*;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConf {
    public static final String QUEUE_NAME = "notification_subscribe";
    public static final String EXCHANGE_NAME = "Exchange";
    public static final String ROUTING_KEY = "testRoutingKey";
    public static final String ANOTHER_QUEUE_NAME = "new_publications";


    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setExchange(EXCHANGE_NAME);
        rabbitTemplate.setRoutingKey(ROUTING_KEY);
        return rabbitTemplate;
    }

    @Bean
    public Queue anotherQueue() {
        return new Queue(ANOTHER_QUEUE_NAME, false);
    }

    @Bean
    public DirectExchange anotherExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding anotherBinding(Queue anotherQueue, DirectExchange anotherExchange) {
        return BindingBuilder.bind(anotherQueue).to(anotherExchange).with(ROUTING_KEY);
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueues(anotherQueue());
        container.setMessageListener(new MessageListenerAdapter(new RabbitMQConsumer()));
        return container;
    }

}
