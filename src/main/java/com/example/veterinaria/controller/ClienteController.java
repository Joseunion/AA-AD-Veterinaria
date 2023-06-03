package com.example.veterinaria.controller;

import com.example.veterinaria.domain.Cliente;
import com.example.veterinaria.domain.Cita;
import com.example.veterinaria.exception.ClienteNotFoundException;
import com.example.veterinaria.exception.ErrorResponse;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.service.ClienteService;
import com.example.veterinaria.service.CitaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Autowired
    private CitaService citaService;

    private final Logger logger = LoggerFactory.getLogger(ClienteController.class);

    // FILTRADO por 3 campos
    @GetMapping("/clientes")
    public List<Cliente> getClientes(@RequestParam(name = "nombre", required = false) String nombre,
                                     @RequestParam(name = "apellido", required = false) String apellido,
                                     @RequestParam(name = "dni", required = false) String dni,
                                     @RequestParam(name = "all", defaultValue = "false") boolean all) {
        List<Cliente> clientes;
        logger.info("Inicio getClientes");
        if (all) {
            logger.info("Mostrado de todos los clientes");
            clientes = clienteService.findAllClientes();
        } else {
            logger.info("Filtrado por nombre, apellido o dni");
            clientes = clienteService.findAllClientes(nombre, apellido, dni);
        }
        logger.info("Fin getClientes");
        return clientes;
    }

    @GetMapping("/cliente/{id}")
    public Cliente getById(@PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio getById " + id);
        Cliente cliente = clienteService.findById(id);
        logger.info("Fin getById " + id);
        return cliente;
    }


    @DeleteMapping("/cliente/{id}")
    public Cliente deleteCliente(@PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio deleteCliente " + id);
        Cliente cliente = clienteService.deleteCliente(id);
        logger.info("Fin deleteCliente " + id);
        return cliente;
    }

    @PostMapping("/cliente")
    public Cliente addCliente(@RequestBody Cliente cliente) {
        logger.info("Inicio addCliente");
        Cliente newCliente = clienteService.addCliente(cliente);
        logger.info("Fin addCliente");
        return newCliente;
    }

    @PutMapping("/cliente/{id}")
    public Cliente modifyCliente(@RequestBody Cliente cliente, @PathVariable long id) throws ClienteNotFoundException {
        logger.info("Inicio modifyCliente " + id);
        Cliente newCliente = clienteService.modifyCliente(id, cliente);
        logger.info("Fin modifyCliente " + id);
        return newCliente;
    }

    @PatchMapping("/cliente/{id}")
    public Cliente modifyNombreCliente(@PathVariable long id, @RequestBody String nombre) throws ClienteNotFoundException {
        logger.info("Inicio modifyEdadCliente " + id + " a nombre " + nombre);
        Cliente cliente = clienteService.modifyNombreCliente(id, nombre);
        logger.info("Fin modifyEdadCliente " + id + " a nombre " + nombre);
        return cliente;
    }

    @GetMapping("/cliente/{id}/facturas")
    public List<Cita> getFacturasPorCliente(@PathVariable long id) throws ClienteNotFoundException, CitaNotFoundException {
        Cliente cliente = clienteService.findById(id);
        List<Cita> citas = citaService.findByCliente(cliente);

        return citas;
    }


    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleClienteNotFoundException(ClienteNotFoundException cnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", cnfe.getMessage());
        logger.info(cnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ClienteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
