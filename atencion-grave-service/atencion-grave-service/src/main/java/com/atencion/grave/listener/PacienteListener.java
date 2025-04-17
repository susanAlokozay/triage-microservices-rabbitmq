package com.atencion.grave.listener;

import com.atencion.grave.model.Paciente;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class PacienteListener {

    private final Random random = new Random();

    @RabbitListener(queues = "queue-grave")
    public void handlePaciente(Paciente paciente) {
        System.out.println("📥 Grave Atención: Recibido paciente -> " + paciente);

        try {
            int seconds = 2 + random.nextInt(4); // delay 2–5 seconds
            System.out.println("🕒 Simulando consulta por " + seconds + " segundos...");
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("✅ Paciente atendido: " + paciente.getDni());
    }
}

