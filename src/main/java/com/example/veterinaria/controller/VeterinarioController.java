package com.example.veterinaria.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.veterinaria.domain.Veterinario;
import com.example.veterinaria.exception.ErrorResponse;
import com.example.veterinaria.exception.VeterinarioNotFoundException;
import com.example.veterinaria.service.VeterinarioService;

public class VeterinarioController {

    @Autowired
    private VeterinarioService veterinarioService;

    private final Logger logger = LoggerFactory.getLogger(VeterinarioController.class);

    @GetMapping("/veterinarios")
    public List<Veterinario> getVeterinario(@RequestParam(value = "nombre", defaultValue = "0") String nombre) throws VeterinarioNotFoundException {
        logger.info("Inicio getVeterinario");
        List<Veterinario> veterinarios;

        if (nombre.equals("0")) {
            logger.info("Mostrado de todos los veterinario");
            logger.info("Fin getVeterinarios");
            return veterinarios = veterinarioService.findAll();
        } else {
            logger.info("Filtrado por nombre de veterinario: " + nombre);
            logger.info("Fin getVeterinarios");
            return veterinarios = veterinarioService.findByNombre(nombre);
        }
    }

    @GetMapping("/veterinario/{id}")
    public Veterinario getVeterinario(@PathVariable long id) throws VeterinarioNotFoundException {
        logger.info("Inicio getVeterinario " + id);
        Veterinario veterinario = veterinarioService.findById(id);
        logger.info("Fin getVeterinario " + id);
        return veterinario;
    }


    @DeleteMapping("/veterinario/{id}")
    public Veterinario deleteVeterinario(@PathVariable long id) throws VeterinarioNotFoundException {
        logger.info("Inicio deleteVeterinario " + id);
        Veterinario veterinario = veterinarioService.deleteVeterinario(id);
        logger.info("Fin deleteVeterinario " + id);
        return veterinario;
    }

    @PostMapping("/veterinario")
    public Veterinario addVeterinario(@RequestBody Veterinario veterinario) {
        logger.info("Inicio addVeterinario");
        Veterinario newVeterinario = veterinarioService.addVeterinario(veterinario);
        logger.info("Fin addVeterinario");
        return newVeterinario;
    }

    @PutMapping("/veterinario/{id}")
    public Veterinario modifyVeterinario(@RequestBody Veterinario veterinario, @PathVariable long id) throws VeterinarioNotFoundException {
        logger.info("Inicio modifyVeterinario " + id);
        Veterinario newVeterinario = veterinarioService.modifyVeterinario(id, veterinario);
        logger.info("Fin modifyVeterinario " + id);
        return newVeterinario;
    }

    @PatchMapping("/veterinario/{id}")
    public Veterinario modifyVeterinarioDisponible(@PathVariable long id, @RequestBody boolean disponible) throws VeterinarioNotFoundException {
        logger.info("Inicio modifyVeterinarioDisponible " + id + " a " + disponible);
        Veterinario veterinario = veterinarioService.modifyCitaTerminada(id, disponible);
        logger.info("Fin modifyVeterinarioDisponible " + id + " a " + disponible);
        return veterinario;
    }

    @ExceptionHandler(VeterinarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleVeterinarioNotFoundException(VeterinarioNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.info(mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(VeterinarioNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
