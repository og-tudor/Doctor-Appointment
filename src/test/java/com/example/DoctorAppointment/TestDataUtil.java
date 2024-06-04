package com.example.DoctorAppointment;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;

import java.util.HashSet;

public class TestDataUtil {
    public TestDataUtil() {
    }

    public static SpecializationEntity createExampleSpecializationA() {
        return SpecializationEntity.builder()
                .name("Dentist")
                .build();
    }

    public static SpecializationEntity createExampleSpecializationB() {
        return SpecializationEntity.builder()
                .name("Cardiology")
                .build();
    }

    public static SpecializationEntity createExampleSpecializationC() {
        return SpecializationEntity.builder()
                .name("Neurology")
                .build();
    }

    public static DoctorEntity createExampleDoctorA() {
        return DoctorEntity.builder()
                .name("Cosmina")
                .age(50)
                .doctorSpecializations(new HashSet<>())
                .appointmentEntities(new HashSet<>())
                .build();
    }

    public static DoctorEntity createExampleDoctorB() {
        return DoctorEntity.builder()
                .name("Bob")
                .age(70)
                .doctorSpecializations(new HashSet<>())
                .appointmentEntities(new HashSet<>())
                .build();
    }

    public static DoctorEntity createExampleDoctorC() {
        return DoctorEntity.builder()
                .id(3L)
                .name("Diana")
                .age(25)
                .doctorSpecializations(new HashSet<>())
                .appointmentEntities(new HashSet<>())
                .build();
    }

    public static PatientEntity createExamplePatientA() {
        return PatientEntity.builder()
                .name("Jane Doe")
                .age(25)
                .build();
    }

    public static PatientEntity createExamplePatientB() {
        return PatientEntity.builder()
                .name("Alice")
                .age(30)
                .build();
    }

    public static PatientEntity createExamplePatientC() {
        return PatientEntity.builder()
                .name("Bob")
                .age(40)
                .build();
    }

    public static AppointmentEntity createExampleAppointmentA() {
        return AppointmentEntity.builder()
                .id(1L)
                .doctorEntity(null)
                .patientEntity(null)
                .date(null)
                .build();
    }


}
