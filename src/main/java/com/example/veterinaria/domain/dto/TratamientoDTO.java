package com.example.veterinaria.domain.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TratamientoDTO {
    
    private boolean ejecutado;
    private long mascota;
    private long veterinario;
}
