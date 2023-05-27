package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Cita;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findAll();
    
}
