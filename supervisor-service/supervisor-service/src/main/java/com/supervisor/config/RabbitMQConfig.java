package com.supervisor.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pacientes-exchange";
    public static final String QUEUE_NAME = "queue-supervisor";
    public static final String ROUTING_KEY = "pacientes.*";

    @Bean
    public Queue supervisorQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue supervisorQueue, TopicExchange exchange) {
        return BindingBuilder.bind(supervisorQueue).to(exchange).with(ROUTING_KEY);
    }
}
