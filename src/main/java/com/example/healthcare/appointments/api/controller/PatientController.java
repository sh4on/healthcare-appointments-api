package com.example.healthcare.appointments.api.controller;

import com.example.healthcare.appointments.api.exception.PatientNotFoundException;
import com.example.healthcare.appointments.api.model.Patient;
import com.example.healthcare.appointments.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PatientController {

    @Autowired
    private PatientService service;

    // Get a list of all patients
    @GetMapping("/patients")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = service.getAllPatients();

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }

    // Get a patient by ID
    @GetMapping("/patients/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getPatient(id), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Add a new patient
    @PostMapping("/patients")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(service.savePatient(patient), HttpStatus.CREATED);
    }

    // Update an existing patient
    @PutMapping("/patients")
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
        try {
            return new ResponseEntity<>(service.updatePatient(patient), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient by ID
    @DeleteMapping("/patients/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            List<Patient> patients = service.deletePatient(id);

            if (patients.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(patients, HttpStatus.OK);
            }
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
