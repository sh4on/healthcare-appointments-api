package com.example.healthcare.appointments.api.controller;

import com.example.healthcare.appointments.api.exception.DoctorNotFoundException;
import com.example.healthcare.appointments.api.model.Doctor;
import com.example.healthcare.appointments.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DoctorController {

    @Autowired
    private DoctorService service;

    // Get a list of doctors with optional specialization filter
    @GetMapping("/doctors")
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam(required = false) String specialization) {
        List<Doctor> doctors;

        if (specialization != null) {
            doctors = service.getAllDoctorsContaining(specialization);
        } else {
            doctors = service.getAllDoctors();
        }

        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Get a doctor by ID
    @GetMapping("/doctors/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(service.getDoctor(id), HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Add a new doctor
    @PostMapping("/doctors")
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(service.saveDoctor(doctor), HttpStatus.CREATED);
    }

    // Update an existing doctor
    @PutMapping("/doctors")
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        try {
            return new ResponseEntity<>(service.updateDoctor(doctor), HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete a doctor by ID
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            List<Doctor> doctors = service.deleteDoctor(id);

            if (doctors.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>(doctors, HttpStatus.OK);
            }

        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
