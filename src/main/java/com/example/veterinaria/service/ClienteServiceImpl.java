package com.example.veterinaria.service;

import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public List<Cliente> findAllClientes() {
        return clienteRepository.findAll();
    }

}