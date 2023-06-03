package com.example.veterinaria.service;

import java.util.List;


import com.example.veterinaria.domain.Cita;
import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.domain.dto.CitaDTO;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.exception.ClienteNotFoundException;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;

public interface CitaService {
    
    List<Cita> findAll();
    
    Cita findById(long id) throws CitaNotFoundException;
    
    List<Cita> findByCliente(Cliente cliente) throws CitaNotFoundException;

    Cita deleteCita(long id) throws CitaNotFoundException;

    List<Cita> findByClienteAndMascota(CitaDTO citaDTO) throws ClienteNotFoundException, MascotaNotFoundException;

    void deleteByMascota(CitaDTO citaDTO) throws MascotaNotFoundException;

    Cita addCita(CitaDTO newCitaDTO) throws
    ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException;

Cita modifyCita(long id, CitaDTO citaDTO) throws CitaNotFoundException,
     ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException;


    
}
