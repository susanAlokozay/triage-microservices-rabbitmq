package com.triage.controller;

import com.triage.model.Paciente;
import com.triage.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.triage.messaging.PacienteSender;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")

public class PacienteController {

	@Autowired
	private PacienteRepository pacienteRepository;

    @Autowired
    private PacienteSender sender;


    @PostMapping("/pacientes")
    public Paciente create(@RequestBody Paciente paciente) {
        Paciente saved = pacienteRepository.save(paciente);
        sender.sendPaciente(saved);
        return saved;
    }


    @GetMapping("/pacientes")
    public List<Paciente> getAllPacientes(
            @RequestParam(required = false) String dni,
            @RequestParam(required = false) String grado,
            @RequestParam(required = false) String estado) {

        if (dni != null) {
            return pacienteRepository.findByDni(dni);
        } else if (grado != null) {
            return pacienteRepository.findByGrado(grado);
        } else if (estado != null) {
            return pacienteRepository.findByEstado(estado);
        }

        return pacienteRepository.findAll();
    }



    // Getting patient by DNI
    @GetMapping("/dni/{dni}")
    public Optional<Paciente> getByDni(@PathVariable String dni) {
        return pacienteRepository.findById(dni);
    }

    // Getting patients by GRADO
    @GetMapping("/grado/{grado}")
    public List<Paciente> getByGrado(@PathVariable String grado) {
        return pacienteRepository.findByGrado(grado);
    }

    // Getting patients by ESTADO
    @GetMapping("/estado/{estado}")
    public List<Paciente> getByEstado(@PathVariable String estado) {
        return pacienteRepository.findByEstado(estado);
    }
    
    @PutMapping("/pacientes/{dni}")
    public ResponseEntity<Paciente> updatePaciente(@PathVariable String dni, @RequestBody Paciente updatedPaciente) {
        return pacienteRepository.findById(dni)
            .map(paciente -> {
                paciente.setNombre(updatedPaciente.getNombre());
                paciente.setApellidos(updatedPaciente.getApellidos());
                paciente.setGenero(updatedPaciente.getGenero());
                paciente.setGrado(updatedPaciente.getGrado());
                paciente.setEstado(updatedPaciente.getEstado());
                
                Paciente saved = pacienteRepository.save(paciente);
                return ResponseEntity.ok(saved);  //this return is essential!
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @DeleteMapping("/pacientes/{dni}")
    public ResponseEntity<Void> deletePaciente(@PathVariable String dni) {
        if (pacienteRepository.existsById(dni)) {
            pacienteRepository.deleteById(dni);
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404
        }
    }


}
