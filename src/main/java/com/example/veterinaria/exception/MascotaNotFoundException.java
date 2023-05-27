package com.example.veterinaria.exception;

public class MascotaNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Mascota no encontrada";

    public MascotaNotFoundException(String message) {
        super(message);
    }

    public MascotaNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}