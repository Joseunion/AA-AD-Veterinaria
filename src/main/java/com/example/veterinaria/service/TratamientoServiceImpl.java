package com.example.veterinaria.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinaria.domain.Mascota;
import com.example.veterinaria.domain.Tratamiento;
import com.example.veterinaria.domain.Veterinario;
import com.example.veterinaria.domain.dto.TratamientoDTO;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;
import com.example.veterinaria.exception.VeterinarioNotFoundException;
import com.example.veterinaria.repository.MascotaRepository;
import com.example.veterinaria.repository.TratamientoRepository;
import com.example.veterinaria.repository.VeterinarioRepository;

@Service
public class TratamientoServiceImpl implements TratamientoService {

    @Autowired
    private TratamientoRepository tratamientoRepository;
    @Autowired
    private VeterinarioRepository veterinarioRepository;
    @Autowired
    private MascotaRepository mascotaRepository;

    @Override
    public List<Tratamiento> findAll() {
        return tratamientoRepository.findAll();
    }

    @Override
    public Tratamiento findById(long id) throws TratamientoNotFoundException {
        return tratamientoRepository.findById(id).orElseThrow(TratamientoNotFoundException::new);
    }

    @Override
    public List<Tratamiento> findByEjecutado(boolean ejecutado) {
        return tratamientoRepository.findByEjecutado(ejecutado);
    }

    @Override
    public Tratamiento deleteTratamiento(long id) throws TratamientoNotFoundException {
        Tratamiento veterinario = tratamientoRepository.findById(id).orElseThrow(TratamientoNotFoundException::new);
        tratamientoRepository.delete(veterinario);
        return veterinario;
    }

    @Override
    public Tratamiento addTratamiento(TratamientoDTO newTratamientoDTO) throws
            VeterinarioNotFoundException, MascotaNotFoundException {

        Mascota mascota = mascotaRepository.findById(newTratamientoDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        Veterinario veterinario = veterinarioRepository.findById(newTratamientoDTO.getVeterinario())
                .orElseThrow(VeterinarioNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Tratamiento tratamiento = mapper.map(newTratamientoDTO, Tratamiento.class);
        tratamiento.setVeterinario(veterinario);
        tratamiento.setMascota(mascota);

        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public Tratamiento modifyTratamiento(long id, TratamientoDTO newTratamientoDTO) throws
            VeterinarioNotFoundException, MascotaNotFoundException {

        Mascota mascota = mascotaRepository.findById(newTratamientoDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        Veterinario veterinario = veterinarioRepository.findById(newTratamientoDTO.getVeterinario())
                .orElseThrow(VeterinarioNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Tratamiento tratamiento = mapper.map(newTratamientoDTO, Tratamiento.class);
        tratamiento.setId(id);
        tratamiento.setVeterinario(veterinario);
        tratamiento.setMascota(mascota);

        return tratamientoRepository.save(tratamiento);
    }

    @Override
    public Tratamiento modifyTratamientoEjecutado(long id, boolean ejecutado) throws TratamientoNotFoundException {
        Tratamiento tratamiento = tratamientoRepository.findById(id).orElseThrow(TratamientoNotFoundException::new);
        tratamiento.setEjecutado(ejecutado);
        return tratamientoRepository.save(tratamiento);
    }
    
}
