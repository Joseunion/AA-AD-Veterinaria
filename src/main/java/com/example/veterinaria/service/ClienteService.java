package com.example.veterinaria.service;

import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.exception.ClienteNotFoundException;

import java.util.List;

public interface ClienteService {

    List<Cliente> findAllClientes();

    List<Cliente> findAllClientes(String nombre, String apellido, String dni);

    Cliente findById(long id) throws ClienteNotFoundException;

    Cliente deleteCliente(long id) throws ClienteNotFoundException;

    Cliente addCliente(Cliente cliente);

    Cliente modifyCliente(long id, Cliente cliente) throws ClienteNotFoundException;

    Cliente modifyNombreCliente(long id, String nombre) throws ClienteNotFoundException;

}
