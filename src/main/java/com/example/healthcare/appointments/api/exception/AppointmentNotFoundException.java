package com.example.healthcare.appointments.api.exception;

public class AppointmentNotFoundException extends RuntimeException {

    public AppointmentNotFoundException() {
    }

    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
