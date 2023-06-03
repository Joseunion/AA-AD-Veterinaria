package com.example.veterinaria.service;

import java.util.List;


import com.example.veterinaria.domain.Veterinario;
import com.example.veterinaria.exception.VeterinarioNotFoundException;

public interface VeterinarioService {
    
    List<Veterinario> findAll();
    
    Veterinario findById(long id) throws VeterinarioNotFoundException;

    List<Veterinario> findByNombre(String nombre) throws VeterinarioNotFoundException;

    Veterinario deleteVeterinario(long id) throws VeterinarioNotFoundException;

    Veterinario addVeterinario(Veterinario veterinario);

    Veterinario modifyVeterinario(long id, Veterinario veterinario) throws VeterinarioNotFoundException;

    Veterinario modifyCitaTerminada(long id, boolean disponible) throws VeterinarioNotFoundException;

    
}
