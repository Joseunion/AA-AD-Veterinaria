package com.example.veterinaria.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "veterinario")
public class Veterinario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String nombre;
    @Column
    public String apellido;
    @Column
    public int edad;
    @Column
    public String dni;
    @Column
    public boolean disponible;
    @OneToMany(mappedBy = "veterinario")
    @JsonBackReference(value = "veterinario-tratamiento")
    private List<Tratamiento> tratamientos;
}
