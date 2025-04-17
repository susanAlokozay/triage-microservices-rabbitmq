package com.atencion.leve.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pacientes-exchange";
    public static final String QUEUE_NAME = "queue-leve";
    public static final String ROUTING_KEY = "pacientes.leve";


    @Bean
    public Queue leveQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue leveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(leveQueue).to(exchange).with(ROUTING_KEY);
    }
}

