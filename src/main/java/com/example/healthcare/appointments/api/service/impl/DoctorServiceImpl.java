package com.example.healthcare.appointments.api.service.impl;

import com.example.healthcare.appointments.api.exception.DoctorNotFoundException;
import com.example.healthcare.appointments.api.model.Doctor;
import com.example.healthcare.appointments.api.repository.DoctorRepository;
import com.example.healthcare.appointments.api.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<Doctor> getAllDoctorsContaining(String specialization) {
        return doctorRepository.findBySpecializationContaining(specialization);
    }

    @Override
    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id).orElseThrow(
                () -> new DoctorNotFoundException(String.format("There is no doctor here with ID %d.", id))
        );
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        // Before updating, checking whether the doctor exists in the database.
        getDoctorById(doctor.getDoctorID());

        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> deleteDoctorById(Long id) {
        doctorRepository.delete(getDoctorById(id));

        // Returns the list of remaining doctors.
        return doctorRepository.findAll();
    }
}
