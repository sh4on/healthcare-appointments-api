package com.example.healthcare.appointments.api.service;

import com.example.healthcare.appointments.api.model.Appointment;

import java.util.List;

public interface AppointmentService {

    Appointment saveAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    Appointment getAppointment(Long id);

    Appointment updateAppointment(Appointment appointment);

    List<Appointment> deleteAppointment(Long id);
}
