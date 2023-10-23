package com.example.healthcare.appointments.api.repository;

import com.example.healthcare.appointments.api.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
