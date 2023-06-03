package com.example.veterinaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinaria.domain.Mascota;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.repository.MascotaRepository;


@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    private MascotaRepository mascotaRepository;


    @Override
    public List<Mascota> findAll() {
        return mascotaRepository.findAll();
    }

    @Override
    public Mascota findById(long id) throws MascotaNotFoundException {
        return mascotaRepository.findById(id).orElseThrow(MascotaNotFoundException::new);
    }

    @Override
    public List<Mascota> findByEspecie(String especie) {
        return mascotaRepository.findByEspecie(especie);
    }

    @Override
    public Mascota deleteMascota(long id) throws MascotaNotFoundException {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(MascotaNotFoundException::new);
        mascotaRepository.delete(mascota);
        return mascota;
    }

    @Override
    public Mascota addMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }

    @Override
    public Mascota modifyMascota(long id, Mascota newMascota) throws MascotaNotFoundException {
        Mascota mascota = mascotaRepository.findById(id).orElseThrow(MascotaNotFoundException::new);
        mascota.setEspecie(newMascota.getEspecie());
        mascota.setNombre(newMascota.getNombre());
        mascota.setEdad(newMascota.getEdad());
        mascota.setPeso(newMascota.getPeso());
        mascota.setCliente(newMascota.getCliente());

        return mascotaRepository.save(mascota);
    }

    
}
