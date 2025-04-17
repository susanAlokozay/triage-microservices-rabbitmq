package com.atencion.grave.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "pacientes-exchange";
    public static final String QUEUE_NAME = "queue-grave";
    public static final String ROUTING_KEY = "pacientes.grave";

    @Bean
    public Queue graveQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue graveQueue, TopicExchange exchange) {
        return BindingBuilder.bind(graveQueue).to(exchange).with(ROUTING_KEY);
    }
}

