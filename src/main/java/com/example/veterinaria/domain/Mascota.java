package com.example.veterinaria.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "mascota")
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;
    @Column
    public String nombre;
    @Column
    public String especie;
    @Column
    public int edad;
    @Column
    public float peso;
    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente cliente;
    @OneToMany(mappedBy = "mascota")
    @JsonBackReference(value = "mascota-cita")
    private List<Cita> citas;
    @OneToMany(mappedBy = "mascota")
    @JsonBackReference(value = "mascota-tratamiento")
    private List<Tratamiento> tratamientos;
    
}
