package com.example.veterinaria.exception;

public class TratamientoNotFoundException extends Exception {

    private static final String DEFAULT_ERROR_MESSAGE = "Tratamiento no encontrado";

    public TratamientoNotFoundException(String message) {
        super(message);
    }

    public TratamientoNotFoundException() {
        super(DEFAULT_ERROR_MESSAGE);
    }
}