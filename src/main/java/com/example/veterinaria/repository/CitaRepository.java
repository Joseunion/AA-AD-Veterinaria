package com.example.veterinaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.veterinaria.domain.Cita;
import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.domain.Mascota;

import jakarta.transaction.Transactional;

@Repository
public interface CitaRepository extends CrudRepository<Cita, Long> {
    List<Cita> findAll();

    List<Cita> findByCliente(Cliente cliente);

    // JPQL
    @Query("SELECT f FROM cita f WHERE cliente = :cliente AND mascota = :mascota")
    List<Cita> findByClienteAndMascota(@Param("cliente") Cliente cliente, @Param("mascota") Mascota mascota);

    // JPQL
    @Transactional
    @Modifying
    @Query("DELETE FROM cita WHERE mascota = :mascota")
    void deleteByMascota(@Param("mascota") Mascota mascota);
}
    
