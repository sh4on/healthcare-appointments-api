package com.example.healthcare.appointments.api.repository;

import com.example.healthcare.appointments.api.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
