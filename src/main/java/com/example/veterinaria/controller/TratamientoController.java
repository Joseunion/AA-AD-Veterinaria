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

import com.example.veterinaria.domain.Tratamiento;
import com.example.veterinaria.domain.dto.TratamientoDTO;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.exception.ErrorResponse;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;
import com.example.veterinaria.exception.VeterinarioNotFoundException;
import com.example.veterinaria.service.TratamientoService;

public class TratamientoController {

    @Autowired
    private TratamientoService tratamientoService;

    private final Logger logger = LoggerFactory.getLogger(TratamientoController.class);

    @GetMapping("/tratamientos")
    public List<Tratamiento> getTratamiento() {
        logger.info("Inicio getTratamiento");
        List<Tratamiento> tratamientos = tratamientoService.findAll();
        logger.info("Fin getTratamiento");
        return tratamientos;
    }

    @GetMapping("/tratamiento/{id}")
    public Tratamiento getById(@PathVariable long id) throws TratamientoNotFoundException {
        logger.info("Inicio getById " + id);
        Tratamiento tratamiento = tratamientoService.findById(id);
        logger.info("Fin getById " + id);
        return tratamiento;
    }

    @GetMapping("/tratamientos/{ejecutado}")
    public List<Tratamiento> getByEjecutado(@PathVariable boolean ejecutado) throws TratamientoNotFoundException {
        logger.info("Inicio getByEjecutado " + ejecutado);
        List<Tratamiento> tratamientos = tratamientoService.findByEjecutado(ejecutado);
        logger.info("Fin getByEjecutado " + ejecutado);
        return tratamientos;
    }

    @DeleteMapping("/tratamiento/{id}")
    public Tratamiento deleteTratamiento(@PathVariable long id) throws TratamientoNotFoundException {
        logger.info("Inicio deleteTratamiento " + id);
        Tratamiento tratamiento = tratamientoService.deleteTratamiento(id);
        logger.info("Fin deleteTratamiento " + id);
        return tratamiento;
    }

    // DTO
    @PostMapping("/tratamiento")
    public Tratamiento addTratamiento(@RequestBody TratamientoDTO newTratamientoDTO) throws
            VeterinarioNotFoundException, MascotaNotFoundException, CitaNotFoundException {
        logger.info("Inicio addTratamiento");
        Tratamiento newTratamiento = tratamientoService.addTratamiento(newTratamientoDTO);
        logger.info("Fin addTratamiento");
        return newTratamiento;
    }

    // DTO
    @PutMapping("/tratamiento/{id}")
    public Tratamiento modifyTratamiento(@RequestBody TratamientoDTO tratamientoDTO, @PathVariable long id) throws TratamientoNotFoundException,
            VeterinarioNotFoundException, MascotaNotFoundException, CitaNotFoundException {
        logger.info("Inicio modifyTratamiento " + id);
        Tratamiento newTratamiento = tratamientoService.modifyTratamiento(id, tratamientoDTO);
        logger.info("Fin modifyTratamiento " + id);
        return newTratamiento;
    }

    @PatchMapping("/tratamiento/{id}")
    public Tratamiento modifyTratamientoEjecutado(@PathVariable long id, @RequestBody boolean ejecutado) throws TratamientoNotFoundException {
        logger.info("Inicio modifyTratamientoEjecutado " + id + " a " + ejecutado);
        Tratamiento tratamiento = tratamientoService.modifyTratamientoEjecutado(id, ejecutado);
        logger.info("Fin modifyTratamientoEjecutado " + id + " a " + ejecutado);
        return tratamiento;
    }

    @ExceptionHandler(TratamientoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleOrdenNotFoundException(TratamientoNotFoundException onfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", onfe.getMessage());
        logger.info(onfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TratamientoNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
