package com.example.healthcare.appointments.api.exception;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException() {
    }

    public PatientNotFoundException(String message) {
        super(message);
    }
}
