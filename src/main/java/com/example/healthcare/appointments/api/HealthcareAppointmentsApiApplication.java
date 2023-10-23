package com.example.healthcare.appointments.api;

import com.example.healthcare.appointments.api.model.Appointment;
import com.example.healthcare.appointments.api.model.Doctor;
import com.example.healthcare.appointments.api.model.Patient;
import com.example.healthcare.appointments.api.repository.AppointmentRepository;
import com.example.healthcare.appointments.api.repository.DoctorRepository;
import com.example.healthcare.appointments.api.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Calendar;
import java.util.Date;

/**
 * Entry point for the "Spring Boot REST CRUD API for Managing Healthcare Appointments."
 * Implements CommandLineRunner to populate the database with dummy data on application startup.
 */

@SpringBootApplication
public class HealthcareAppointmentsApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(HealthcareAppointmentsApiApplication.class, args);
    }

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Override
    public void run(String... args) throws Exception {
        // This method is executed on application startup to insert dummy data into the database.
        // Dummy data for Doctor, Patient, and Appointment entities is created and saved.

        // Create dummy data for Doctor

        Doctor doctor1 = new Doctor();
        doctor1.setFirstName("John");
        doctor1.setLastName("Smith");
        doctor1.setSpecialization("Cardiologist");
        doctor1.setPhoneNumber("+1234567890");
        doctor1.setEmail("john.smith@example.com");

        // Create dummy data for Patient 1

        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(1996, 11, 31, 0, 0, 0);
        Date patient1DateOfBirth = calendar1.getTime();

        Patient patient1 = new Patient();
        patient1.setFirstName("Alice");
        patient1.setLastName("Johnson");
        patient1.setDateOfBirth(patient1DateOfBirth);
        patient1.setPhoneNumber("+1122334455");
        patient1.setEmail("alice.johnson@example.com");

        // Create dummy data for Patient 2

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(1998, 0, 31, 0, 0, 0);
        Date patient2DateOfBirth = calendar2.getTime();

        Patient patient2 = new Patient();
        patient2.setFirstName("Robert");
        patient2.setDateOfBirth(patient2DateOfBirth);
        patient2.setPhoneNumber("+9988776655");

        // Create dummy data for Patient 3

        Calendar calendar3 = Calendar.getInstance();
        calendar3.set(1996, 11, 31, 0, 0, 0);
        Date patient3DateOfBirth = calendar3.getTime();

        Patient patient3 = new Patient();
        patient3.setFirstName("William");
        patient3.setLastName("Smith");
        patient3.setDateOfBirth(patient3DateOfBirth);
        patient3.setPhoneNumber("+1122334423");
        patient3.setEmail("will.smith@example.com");

        // Create dummy data for Appointment 1

        Calendar calendar4 = Calendar.getInstance();
        calendar4.set(2023, 9, 20, 0, 0, 0);
        Date appointment1Date = calendar4.getTime();

        Appointment appointment1 = new Appointment();
        appointment1.setDate(appointment1Date);
        appointment1.setPurpose("Checkup");
        appointment1.setDoctor(doctor1);
        appointment1.setPatient(patient1);

        // Create dummy data for Appointment 2

        Calendar calendar5 = Calendar.getInstance();
        calendar5.set(2023, 9, 20, 0, 0, 0);
        Date appointment2Date = calendar5.getTime();

        Appointment appointment2 = new Appointment();
        appointment2.setDate(appointment2Date);
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
