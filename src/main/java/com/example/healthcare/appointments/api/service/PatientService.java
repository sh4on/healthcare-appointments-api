package com.example.healthcare.appointments.api.service;

import com.example.healthcare.appointments.api.model.Patient;

import java.util.List;

public interface PatientService {

    Patient savePatient(Patient patient);

    List<Patient> getAllPatients();

    Patient getPatient(Long id);

    Patient updatePatient(Patient patient);

    List<Patient> deletePatient(Long id);
}
