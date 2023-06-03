package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Tratamiento;

@Repository
public interface TratamientoRepository extends CrudRepository<Tratamiento, Long> {
    List<Tratamiento> findAll();

    List<Tratamiento> findByEjecutado(boolean ejecutado);    
}
