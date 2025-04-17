package com.atencion.critico.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pacientes-exchange";
    public static final String QUEUE_NAME = "queue-critico";
    public static final String ROUTING_KEY = "pacientes.critico";

    @Bean
    public Queue criticoQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue criticoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(criticoQueue).to(exchange).with(ROUTING_KEY);
    }
}

