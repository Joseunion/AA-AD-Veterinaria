package com.example.veterinaria.service;

import java.util.List;

import com.example.veterinaria.domain.Mascota;
import com.example.veterinaria.exception.MascotaNotFoundException;

public interface MascotaService {
    
    List<Mascota> findAll();
    
    Mascota findById(long id) throws MascotaNotFoundException;

    List<Mascota> findByEspecie(String especie) throws MascotaNotFoundException;

    Mascota deleteMascota(long id) throws MascotaNotFoundException;

    Mascota addMascota(Mascota mascota);

    Mascota modifyMascota(long id, Mascota mascota) throws MascotaNotFoundException;

    
}
