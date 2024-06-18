package com.example.notification.config;

import com.example.notification.service.RabbitMQConsumer;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class RabbitMQConfig {

    public static final String ANOTHER_QUEUE_NAME = "notification_subscribe";

    public static final String ANOTHER_EXCHANGE_NAME = "Exchange";

    public static final String ANOTHER_ROUTING_KEY = "testRoutingKey";

    @Bean
    public Queue anotherQueue() {
        return new Queue(ANOTHER_QUEUE_NAME, false);
    }

    @Bean
    public DirectExchange anotherExchange() {
        return new DirectExchange(ANOTHER_EXCHANGE_NAME);
    }

    @Bean
    public Binding anotherBinding(Queue anotherQueue, DirectExchange anotherExchange) {
        return BindingBuilder.bind(anotherQueue).to(anotherExchange).with(ANOTHER_ROUTING_KEY);
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
