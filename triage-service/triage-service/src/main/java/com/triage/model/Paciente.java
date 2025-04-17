package com.triage.model;
import java.io.Serializable;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Paciente implements Serializable {
	  @Id
	    private String dni;
	    private String nombre;
	    private String apellidos;
	    private String genero;
	    private String grado; 
	    private String estado; 
}
