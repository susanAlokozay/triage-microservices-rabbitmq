package com.supervisor.listener;

import com.supervisor.model.Paciente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PacienteListener {

    @RabbitListener(queues = "queue-supervisor")
    public void handlePaciente(Paciente paciente) {
        System.out.println("👁️ Supervisor recibió paciente: " + paciente);
    }
}

