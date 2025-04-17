package com.triage.messaging;

import com.triage.config.RabbitMQConfig;
import com.triage.model.Paciente;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendPaciente(Paciente paciente) {
        String routingKey = "pacientes." + paciente.getGrado(); 

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                routingKey,
                paciente
        );

        System.out.println("Sent to RabbitMQ -> " + routingKey + ": " + paciente);
    }
}
