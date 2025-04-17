package com.triage.repository;

import com.triage.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PacienteRepository extends JpaRepository<Paciente, String> {
	List<Paciente> findByDni(String dni);
    List<Paciente> findByGrado(String grado);
    List<Paciente> findByEstado(String estado);
}

