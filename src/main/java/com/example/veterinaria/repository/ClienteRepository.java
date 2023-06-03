package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Cliente;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    List<Cliente> findAll();

    List<Cliente> findByNombreOrApellidoOrDni(String nombre, String apellido, String dni);

    
}
