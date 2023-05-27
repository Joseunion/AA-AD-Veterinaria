package com.example.veterinaria.exception;

public class CitaNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Cita no encontrada";

    public CitaNotFoundException(String message) {
        super(message);
    }

    public CitaNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}