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
    private PatientRepository patientRepository;

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientRepository.findById(id).orElseThrow(
                () -> new PatientNotFoundException(String.format("There is no patient here with ID %d.", id))
        );
    }

    @Override
    public Patient updatePatient(Patient patient) {
        // Before updating, checking whether the patient exists in the database.
        getPatientById(patient.getPatientID());

        return patientRepository.save(patient);
    }

    @Override
    public List<Patient> deletePatientById(Long id) {
        patientRepository.delete(getPatientById(id));

        // Returns the list of remaining patients.
        return patientRepository.findAll();
    }
}
