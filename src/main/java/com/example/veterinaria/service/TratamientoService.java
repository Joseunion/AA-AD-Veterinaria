package com.example.veterinaria.service;

import java.util.List;

import com.example.veterinaria.domain.Tratamiento;
import com.example.veterinaria.domain.dto.TratamientoDTO;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;
import com.example.veterinaria.exception.VeterinarioNotFoundException;

public interface TratamientoService {
    
    List<Tratamiento> findAll();
    
    Tratamiento findById(long id) throws TratamientoNotFoundException;

    List<Tratamiento> findByEjecutado(boolean ejecutado) throws TratamientoNotFoundException;

    Tratamiento deleteTratamiento(long id) throws TratamientoNotFoundException;

    Tratamiento addTratamiento(TratamientoDTO newTratamientoDTO) throws
            VeterinarioNotFoundException, MascotaNotFoundException, CitaNotFoundException;

            Tratamiento modifyTratamiento(long id, TratamientoDTO tratamientoDTO) throws TratamientoNotFoundException,
            VeterinarioNotFoundException, MascotaNotFoundException, CitaNotFoundException;

            Tratamiento modifyTratamientoEjecutado(long id, boolean ejecutado) throws TratamientoNotFoundException;

    
}
