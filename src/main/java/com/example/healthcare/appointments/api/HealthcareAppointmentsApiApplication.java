package com.example.healthcare.appointments.api;

import com.example.healthcare.appointments.api.model.Appointment;
import com.example.healthcare.appointments.api.model.ContactInformation;
import com.example.healthcare.appointments.api.model.Doctor;
import com.example.healthcare.appointments.api.model.Patient;
import com.example.healthcare.appointments.api.repository.AppointmentRepository;
import com.example.healthcare.appointments.api.repository.DoctorRepository;
import com.example.healthcare.appointments.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Date;

/**
 * Entry point for the "Spring Boot REST CRUD API for Managing Healthcare Appointments."
 * Implements CommandLineRunner to populate the database with dummy data on application startup.
 */

@SpringBootApplication
public class HealthcareAppointmentsApiApplication implements CommandLineRunner {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    public static void main(String[] args) {
        SpringApplication.run(HealthcareAppointmentsApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // This method is executed on application startup to insert dummy data into the database.

        // Create dummy data for Doctor

        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("John");
        doctor1.setLastName("Smith");
        doctor1.setSpecialization("Cardiologist");
        doctor1.setContactInformation(new ContactInformation("+1234567890", "john.smith@example.com"));

        // Create dummy data for Patient 1

        Patient patient1 = new Patient();
        patient1.setFirstName("Alice");
        patient1.setLastName("Johnson");
        patient1.setDateOfBirth(Date.valueOf("1996-11-30"));
        patient1.setContactInformation(new ContactInformation("+1122334455", "alice.johnson@example.com"));

        // Create dummy data for Patient 2

        Patient patient2 = new Patient();
        patient2.setFirstName("Robert");
        patient2.setDateOfBirth(Date.valueOf("1998-1-29"));
        patient2.setContactInformation(new ContactInformation("+9988776655", null));

        // Create dummy data for Patient 3

        Patient patient3 = new Patient();
        patient3.setFirstName("William");
        patient3.setLastName("Smith");
        patient3.setDateOfBirth(Date.valueOf("1996-12-31"));
        patient3.setContactInformation(new ContactInformation("+1122334423", "will.smith@example.com"));

        // Create dummy data for Appointment 1

        Appointment appointment1 = new Appointment();
        appointment1.setAppointmentDate(Date.valueOf("2023-9-20"));
        appointment1.setPurpose("Checkup");
        appointment1.setDoctor(doctor1);
        appointment1.setPatient(patient1);

        // Create dummy data for Appointment 2

        Appointment appointment2 = new Appointment();
        appointment2.setAppointmentDate(Date.valueOf("2023-9-20"));
        appointment2.setPurpose("Consultation");
        appointment2.setDoctor(doctor1);
        appointment2.setPatient(patient2);

        doctorRepository.save(doctor1);

        patientRepository.save(patient1);
        patientRepository.save(patient2);
        patientRepository.save(patient3);

        appointmentRepository.save(appointment1);
        appointmentRepository.save(appointment2);
    }
}
