package com.example.healthcare.appointments.api.controller;

import com.example.healthcare.appointments.api.exception.AppointmentNotFoundException;
import com.example.healthcare.appointments.api.model.Appointment;
import com.example.healthcare.appointments.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AppointmentController {

    @Autowired
    private AppointmentService service;

    // Get a list of all appointments
    @GetMapping("/appointments")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        List<Appointment> appointments = service.getAllAppointments();

        if (appointments.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        }
    }

    // Get an appointment by ID
    @GetMapping("/appointments/{id}")
    public ResponseEntity<?> getAppointment(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getAppointment(id), HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Add a new appointment
    @PostMapping("/appointments")
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment appointment) {

        return new ResponseEntity<>(service.saveAppointment(appointment), HttpStatus.CREATED);
    }

    // Update an existing appointment
    @PutMapping("/appointments")
    public ResponseEntity<?> updateAppointment(@RequestBody Appointment appointment) {
        try {
            return new ResponseEntity<>(service.updateAppointment(appointment), HttpStatus.OK);
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete an appointment by ID
    @DeleteMapping("/appointments/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        try {
            List<Appointment> appointments = service.deleteAppointment(id);

            if (appointments.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(appointments, HttpStatus.OK);
            }
        } catch (AppointmentNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
