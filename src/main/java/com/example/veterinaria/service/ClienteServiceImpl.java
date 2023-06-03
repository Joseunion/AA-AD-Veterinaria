package com.example.veterinaria.service;

import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.exception.ClienteNotFoundException;
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

    @Override
    public List<Cliente> findAllClientes(String nombre, String apellido, String dni) {
        return clienteRepository.findByNombreOrApellidoOrDni(nombre, apellido, dni);
    }

    @Override
    public Cliente findById(long id) throws ClienteNotFoundException {
        return clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
    }


    @Override
    public Cliente deleteCliente(long id) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
        clienteRepository.delete(cliente);
        return cliente;
    }

    @Override
    public Cliente addCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modifyCliente(long id, Cliente newCliente) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id).orElseThrow(ClienteNotFoundException::new);
        cliente.setNombre(newCliente.getNombre());
        cliente.setApellido(newCliente.getApellido());
        cliente.setEdad(newCliente.getEdad());
        cliente.setDni(newCliente.getDni());
        cliente.setDireccion(newCliente.getDireccion());
        

        return clienteRepository.save(cliente);
    }

    @Override
    public Cliente modifyNombreCliente(long id, String nombre) throws ClienteNotFoundException {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(ClienteNotFoundException::new);
        cliente.setNombre(nombre);
        return clienteRepository.save(cliente);
    }

}