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
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    // Add a new patient
    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        return new ResponseEntity<>(service.savePatient(patient), HttpStatus.CREATED);
    }

    // Get a list of all patients
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = service.getAllPatients();

        if (patients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(patients, HttpStatus.OK);
        }
    }

    // Get a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPatient(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getPatientById(id), HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing patient
    @PutMapping
    public ResponseEntity<?> updatePatient(@RequestBody Patient patient) {
        try {
            Patient updatedPatient = service.updatePatient(patient);
            return new ResponseEntity<>(updatedPatient, HttpStatus.OK);
        } catch (PatientNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete a patient by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        try {
            // Get the list of remaining patients.
            List<Patient> patients = service.deletePatientById(id);

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
