package com.example.healthcare.appointments.api.service;

import com.example.healthcare.appointments.api.model.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor saveDoctor(Doctor doctor);

    List<Doctor> getAllDoctors();

    Doctor getDoctorById(Long id);

    Doctor updateDoctor(Doctor doctor);

    List<Doctor> deleteDoctorById(Long id);

    List<Doctor> getAllDoctorsContaining(String specialization);
}
