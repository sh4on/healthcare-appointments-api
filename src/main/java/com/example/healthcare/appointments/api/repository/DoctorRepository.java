package com.example.healthcare.appointments.api.repository;

import com.example.healthcare.appointments.api.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    // SQL: SELECT * FROM doctors WHERE specialization LIKE '%specialization%'
    // HQL: FROM Doctor WHERE specialization LIKE :specialization
    List<Doctor> findBySpecializationContaining(String specialization);
}
