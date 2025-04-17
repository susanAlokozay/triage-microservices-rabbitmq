package com.atencion.leve.listener;

import com.atencion.leve.model.Paciente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PacienteListener {

    private final Random random = new Random();

    @RabbitListener(queues = "queue-leve")
    public void handlePaciente(Paciente paciente) {
        System.out.println("📥 Leve Atención: Recibido paciente -> " + paciente);

        try {
            int seconds = 2 + random.nextInt(4);
            System.out.println("🕒 Simulando consulta por " + seconds + " segundos...");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ Paciente atendido: " + paciente.getDni());
    }
}


