package com.example.veterinaria.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tratamiento")
public class Tratamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public boolean ejecutado;
    @Column
    public String descripcion;
    @Column
    public float precio;
    @ManyToOne
    @JoinColumn(name = "id_mascota")
    public Mascota mascota;
    @ManyToOne
    @JoinColumn(name = "id_veterinario")
    public Veterinario veterinario;
    

    
}
