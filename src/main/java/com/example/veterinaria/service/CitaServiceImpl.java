package com.example.veterinaria.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.veterinaria.domain.Cita;
import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.domain.Mascota;
import com.example.veterinaria.domain.Tratamiento;
import com.example.veterinaria.domain.dto.CitaDTO;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.exception.ClienteNotFoundException;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;
import com.example.veterinaria.repository.CitaRepository;
import com.example.veterinaria.repository.ClienteRepository;
import com.example.veterinaria.repository.MascotaRepository;
import com.example.veterinaria.repository.TratamientoRepository;

@Service
public class CitaServiceImpl implements CitaService {

    @Autowired
    private CitaRepository citaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private MascotaRepository mascotaRepository;
    @Autowired
    private TratamientoRepository tratamientoRepository;


    @Override
    public List<Cita> findAll() {
        return citaRepository.findAll();
    }

    @Override
    public Cita findById(long id) throws CitaNotFoundException {
        return citaRepository.findById(id).orElseThrow(CitaNotFoundException::new);
    }

    @Override
    public Cita deleteCita(long id) throws CitaNotFoundException {
        Cita cita = citaRepository.findById(id).orElseThrow(CitaNotFoundException::new);
        citaRepository.delete(cita);
        return cita;
    }

    @Override
    public Cita addCita(CitaDTO newCitaDTO) throws
           ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException {

        Cliente cliente = clienteRepository.findById(newCitaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Mascota mascota = mascotaRepository.findById(newCitaDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        Tratamiento tratamiento = tratamientoRepository.findById(newCitaDTO.getTratamiento())
                .orElseThrow(TratamientoNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Cita cita = mapper.map(newCitaDTO, Cita.class);
        cita.setCliente(cliente);
        cita.setMascota(mascota);
        cita.setTratamiento(tratamiento);

        return citaRepository.save(cita);
    }

    @Override
    public Cita modifyCita(long id, CitaDTO newCitaDTO) throws
         ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException {

        Cliente cliente = clienteRepository.findById(newCitaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Mascota mascota = mascotaRepository.findById(newCitaDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        Tratamiento tratamiento = tratamientoRepository.findById(newCitaDTO.getTratamiento())
                .orElseThrow(TratamientoNotFoundException::new);

        ModelMapper mapper = new ModelMapper();
        Cita cita = mapper.map(newCitaDTO, Cita.class);
        cita.setId(id);
        cita.setCliente(cliente);
        cita.setMascota(mascota);
        cita.setTratamiento(tratamiento);

        return citaRepository.save(cita);
    }

    @Override
    public List<Cita> findByCliente(Cliente cliente) throws CitaNotFoundException {
        List<Cita> citas = citaRepository.findByCliente(cliente);
        return citas;
    }

    @Override
    public List<Cita> findByClienteAndMascota(CitaDTO citaDTO) throws ClienteNotFoundException, MascotaNotFoundException {

        Cliente cliente = clienteRepository.findById(citaDTO.getCliente())
                .orElseThrow(ClienteNotFoundException::new);

        Mascota mascota = mascotaRepository.findById(citaDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        return citaRepository.findByClienteAndMascota(cliente, mascota);
    }

    @Override
    public void deleteByMascota(CitaDTO citaDTO) throws MascotaNotFoundException {

        Mascota mascota = mascotaRepository.findById(citaDTO.getMascota())
                .orElseThrow(MascotaNotFoundException::new);

        citaRepository.deleteByMascota(mascota);
    }
    
    
}
