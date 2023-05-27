package com.example.veterinaria.exception;

public class VeterinarioNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Veterinario no encontrado";

    public VeterinarioNotFoundException(String message) {
        super(message);
    }

    public VeterinarioNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}