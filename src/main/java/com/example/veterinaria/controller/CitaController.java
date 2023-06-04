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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.veterinaria.domain.Cita;
import com.example.veterinaria.domain.dto.CitaDTO;
import com.example.veterinaria.exception.CitaNotFoundException;
import com.example.veterinaria.exception.ClienteNotFoundException;
import com.example.veterinaria.exception.ErrorResponse;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.exception.TratamientoNotFoundException;
import com.example.veterinaria.service.CitaService;

@RestController
public class CitaController {

    @Autowired
    private CitaService citaService;

    private final Logger logger = LoggerFactory.getLogger(CitaController.class);

    @GetMapping("/citas")
    public List<Cita> getFacturas() {
        logger.info("Inicio getFacturas");
        List<Cita> citas = citaService.findAll();
        logger.info("Fin getFacturas");
        return citas;
    }

    @GetMapping("/cita/{id}")
    public Cita getById(@PathVariable long id) throws CitaNotFoundException {
        logger.info("Inicio getById " + id);
        Cita cita = citaService.findById(id);
        logger.info("Fin getById " + id);
        return cita;
    }

    @DeleteMapping("/cita/{id}")
    public Cita deleteCita(@PathVariable long id) throws CitaNotFoundException {
        logger.info("Inicio deleteCita " + id);
        Cita cita = citaService.deleteCita(id);
        logger.info("Fin deleteCita " + id);
        return cita;
    }

    // DTO
    @PostMapping("/factura")
    public Cita addCita(@RequestBody CitaDTO citaDTO) throws
            ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException {
        logger.info("Inicio addCita");
        Cita newCita = citaService.addCita(citaDTO);
        logger.info("Fin addCita");
        return newCita;
    }

    // DTO
    @PutMapping("/cita/{id}")
    public Cita modifyCita(@RequestBody CitaDTO citaDTO, @PathVariable long id) throws
            CitaNotFoundException, ClienteNotFoundException, MascotaNotFoundException, TratamientoNotFoundException {
        logger.info("Inicio modifyCita " + id);
        Cita newCita = citaService.modifyCita(id, citaDTO);
        logger.info("Fin modifyCita " + id);
        return newCita;
    }


    @ExceptionHandler(CitaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCitaNotFoundException(CitaNotFoundException fnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", fnfe.getMessage());
        logger.info(fnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CitaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
