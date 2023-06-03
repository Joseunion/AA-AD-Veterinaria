package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Veterinario;

@Repository
public interface VeterinarioRepository extends CrudRepository<Veterinario, Long> {
    
    List<Veterinario> findAll();

    List<Veterinario> findByNombre(String nombre);
    
}
