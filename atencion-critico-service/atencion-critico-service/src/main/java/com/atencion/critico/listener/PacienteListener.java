package com.atencion.critico.listener;

import com.atencion.critico.model.Paciente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PacienteListener {

    private final Random random = new Random();

    @RabbitListener(queues = "queue-critico")
    public void handlePaciente(Paciente paciente) {
        System.out.println("🚨 Crítico Atención: Recibido paciente -> " + paciente);

        try {
            int seconds = 3 + random.nextInt(3); // Simulate delay 3–5 sec
            System.out.println("🕒 Simulando atención crítica por " + seconds + " segundos...");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ Paciente crítico atendido: " + paciente.getDni());
    }
}

