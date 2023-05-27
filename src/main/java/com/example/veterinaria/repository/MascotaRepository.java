package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Mascota;

@Repository
public interface MascotaRepository extends CrudRepository<Mascota, Long> {
    List<Mascota> findAll();
    
}
