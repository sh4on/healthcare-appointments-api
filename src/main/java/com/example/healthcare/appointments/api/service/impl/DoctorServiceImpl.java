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
    private DoctorRepository repository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return repository.findAll();
    }

    @Override
    public Doctor getDoctor(Long id) {
        return repository.findById(id).orElseThrow(
                () -> new DoctorNotFoundException(String.format("There is no doctor here with ID %d.", id))
        );
    }

    @Override
    public Doctor updateDoctor(Doctor doctor) {
        // Before updating, checking whether the doctor exists in the database.
        getDoctor(doctor.getDoctorID());

        return repository.save(doctor);
    }

    @Override
    public List<Doctor> deleteDoctor(Long id) {
        repository.delete(getDoctor(id));
        return repository.findAll();
    }

    @Override
    public List<Doctor> getAllDoctorsContaining(String specialization) {
        return repository.findBySpecializationContaining(specialization);
    }
}
