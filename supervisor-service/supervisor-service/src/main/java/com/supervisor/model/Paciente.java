package com.supervisor.model;

import lombok.Data;

@Data
public class Paciente {
    private String dni;
    private String nombre;
    private String apellidos;
    private String genero;
    private String grado;
    private String estado;
}

