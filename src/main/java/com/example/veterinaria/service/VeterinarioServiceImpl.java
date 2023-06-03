package com.example.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinaria.domain.Veterinario;
import com.example.veterinaria.exception.VeterinarioNotFoundException;
import com.example.veterinaria.repository.VeterinarioRepository;

@Service
public class VeterinarioServiceImpl implements VeterinarioService {

    @Autowired
    private VeterinarioRepository veterinarioRepository;
    

    @Override
    public List<Veterinario> findAll() {
        return veterinarioRepository.findAll();
    }

    @Override
    public Veterinario findById(long id) throws VeterinarioNotFoundException {
        return veterinarioRepository.findById(id).orElseThrow(VeterinarioNotFoundException::new);
    }

    @Override
    public List<Veterinario> findByNombre(String nombre) {
        return veterinarioRepository.findByNombre(nombre);
    }

    @Override
    public Veterinario deleteVeterinario(long id) throws VeterinarioNotFoundException {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(VeterinarioNotFoundException::new);
        veterinarioRepository.delete(veterinario);
        return veterinario;
    }

    @Override
    public Veterinario addVeterinario(Veterinario veterinario) {
        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Veterinario modifyVeterinario(long id, Veterinario newVeterinario) throws VeterinarioNotFoundException {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(VeterinarioNotFoundException::new);
        veterinario.setNombre(newVeterinario.getNombre());
        veterinario.setApellido(newVeterinario.getApellido());
        veterinario.setEdad(newVeterinario.getEdad());
        veterinario.setDni(newVeterinario.getDni());
        veterinario.setDisponible(newVeterinario.isDisponible());

        return veterinarioRepository.save(veterinario);
    }

    @Override
    public Veterinario modifyCitaTerminada(long id, boolean disponible) throws VeterinarioNotFoundException {
        Veterinario veterinario = veterinarioRepository.findById(id).orElseThrow(VeterinarioNotFoundException::new);
        veterinario.setDisponible(disponible);
        return veterinarioRepository.save(veterinario);
    }

        
}
