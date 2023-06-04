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

import com.example.veterinaria.domain.Mascota;
import com.example.veterinaria.exception.ErrorResponse;
import com.example.veterinaria.exception.MascotaNotFoundException;
import com.example.veterinaria.service.MascotaService;

@RestController
public class MascotaController {

    @Autowired
    private MascotaService mascotaService;

    private final Logger logger = LoggerFactory.getLogger(MascotaController.class);

    @GetMapping("/mascotas")
    public List<Mascota> getMascotas() {
        logger.info("Inicio getMascotas");
        List<Mascota> mascotas = mascotaService.findAll();
        logger.info("Fin getMascotas");
        return mascotas;
    }

    @GetMapping("/mascota/{id}")
    public Mascota getById(@PathVariable long id) throws MascotaNotFoundException {
        logger.info("Inicio getById " + id);
        Mascota mascota = mascotaService.findById(id);
        logger.info("Fin getById " + id);
        return mascota;
    }

    @GetMapping("/mascotas/{especie}")
    public List<Mascota> getByEspecie(@PathVariable String especie) throws MascotaNotFoundException {
        logger.info("Inicio getByEspecie " + especie);
        List<Mascota> mascotas = mascotaService.findByEspecie(especie);
        logger.info("Fin getByEspecie " + especie);
        return mascotas;
    }

    @DeleteMapping("/mascota/{id}")
    public Mascota deleteMascota(@PathVariable long id) throws MascotaNotFoundException {
        logger.info("Inicio deleteMascota " + id);
        Mascota mascota = mascotaService.deleteMascota(id);
        logger.info("Fin deleteMascota " + id);
        return mascota;
    }

    @PostMapping("/mascota")
    public Mascota addMascota(@RequestBody Mascota mascota) {
        logger.info("Inicio addMascota");
        Mascota newMascota = mascotaService.addMascota(mascota);
        logger.info("Fin addMascota");
        return newMascota;
    }

    @PutMapping("/mascota/{id}")
    public Mascota modifyMascota(@RequestBody Mascota mascota, @PathVariable long id) throws MascotaNotFoundException {
        logger.info("Inicio modifyMascota " + id);
        Mascota newMascota = mascotaService.modifyMascota(id, mascota);
        logger.info("Fin modifyMascota " + id);
        return newMascota;
    }


    @ExceptionHandler(MascotaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleMascotaNotFoundException(MascotaNotFoundException mnfe) {
        ErrorResponse errorResponse = new ErrorResponse("404", mnfe.getMessage());
        logger.info(mnfe.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MascotaNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        ErrorResponse errorResponse = new ErrorResponse("999", "Internal server error");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
