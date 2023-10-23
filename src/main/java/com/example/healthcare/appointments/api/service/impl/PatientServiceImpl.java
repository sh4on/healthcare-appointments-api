package com.example.healthcare.appointments.api.service.impl;

import com.example.healthcare.appointments.api.exception.PatientNotFoundException;
import com.example.healthcare.appointments.api.model.Patient;
import com.example.healthcare.appointments.api.repository.PatientRepository;
import com.example.healthcare.appointments.api.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository repository;

    @Override
    public Patient savePatient(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return repository.findAll();
    }

    @Override
    public Patient getPatient(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new PatientNotFoundException(String.format("There is no patient here with ID %d.", id))
        );
    }

    @Override
    public Patient updatePatient(Patient patient) {
        // Before updating, checking whether the patient exists in the database.
        getPatient(patient.getPatientID());

        return repository.save(patient);
    }

    @Override
    public List<Patient> deletePatient(Long id) {
        repository.delete(getPatient(id));
        return repository.findAll();
    }
}
