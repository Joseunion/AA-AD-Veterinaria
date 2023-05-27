package com.example.veterinaria.domain;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "cita")
public class Cita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public int duracion;
    @Column
    public String descripcion;
    @Column(name = "fecha_cita")
    public LocalDate fechaCita;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente cliente;
    @ManyToOne
    @JoinColumn(name = "id_mascota")
    public Mascota mascota;
    @ManyToOne
    @JoinColumn(name = "id_tratamiento")
    public Tratamiento tratamiento;
    
}
