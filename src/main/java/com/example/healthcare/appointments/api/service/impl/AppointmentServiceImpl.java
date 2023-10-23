package com.example.healthcare.appointments.api.service.impl;

import com.example.healthcare.appointments.api.exception.AppointmentNotFoundException;
import com.example.healthcare.appointments.api.model.Appointment;
import com.example.healthcare.appointments.api.repository.AppointmentRepository;
import com.example.healthcare.appointments.api.repository.DoctorRepository;
import com.example.healthcare.appointments.api.repository.PatientRepository;
import com.example.healthcare.appointments.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointment(Long id) {
        return appointmentRepository.findById(id).orElseThrow(
                () -> new AppointmentNotFoundException(String.format("No appointment found with ID %d.", id))
        );
    }

    @Override
    public Appointment updateAppointment(Appointment appointment) {
        // Before updating, checking whether the appointment exists in the database.
        getAppointment(appointment.getAppointmentID());

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> deleteAppointment(Long id) {
        appointmentRepository.delete(getAppointment(id));
        return appointmentRepository.findAll();
    }
}
