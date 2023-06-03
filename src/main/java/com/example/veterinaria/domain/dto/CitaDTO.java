package com.example.veterinaria.domain.dto;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CitaDTO {

    private LocalDate fechaCita;
    private int duracion;
    private String descripcion;
    private long cliente;
    private long mascota;
    private long tratamiento;
    
}
