package com.atencion.critico.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Paciente implements Serializable {
    private String dni;
    private String nombre;
    private String apellidos;
    private String genero;
    private String grado;
    private String estado;
}

