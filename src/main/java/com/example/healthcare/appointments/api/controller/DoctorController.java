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
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;


    // Add a new doctor
    @PostMapping
    public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor) {
        return new ResponseEntity<>(doctorService.saveDoctor(doctor), HttpStatus.CREATED);
    }

    // Get the list of doctors with optional specialization filter
    @GetMapping
    public ResponseEntity<List<Doctor>> getAllDoctors(@RequestParam(required = false) String specialization) {
        List<Doctor> doctors;

        if (specialization != null) {
            doctors = doctorService.getAllDoctorsContaining(specialization);
        } else {
            doctors = doctorService.getAllDoctors();
        }

        if (doctors.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    // Get a doctor by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(doctorService.getDoctorById(id), HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Update an existing doctor
    @PutMapping
    public ResponseEntity<?> updateDoctor(@RequestBody Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorService.updateDoctor(doctor);
            return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
        } catch (DoctorNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Delete a doctor by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        try {
            // Get the list of remaining doctors.
            List<Doctor> doctors = doctorService.deleteDoctorById(id);

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
