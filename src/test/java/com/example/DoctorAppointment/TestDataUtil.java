package com.example.DoctorAppointment;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;

public class TestDataUtil {
    public TestDataUtil() {
    }

    public static SpecializationEntity createExampleSpecializationA() {
        return SpecializationEntity.builder()
                .name("Cardiology")
                .build();
    }

    public static SpecializationEntity createExampleSpecializationB() {
        return SpecializationEntity.builder()
                .name("Dermatology")
                .build();
    }

    public static SpecializationEntity createExampleSpecializationC() {
        return SpecializationEntity.builder()
                .name("Neurology")
                .build();
    }

    public static DoctorEntity createExampleDoctorA(final SpecializationEntity specializationEntity) {
        return DoctorEntity.builder()
                .id(1L)
                .name("John Doe")
                .age(35)
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
