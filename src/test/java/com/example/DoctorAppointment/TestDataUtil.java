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
                .id(1L)
                .name("Cardiology")
                .doctorEntities(null)
                .build();
    }

    public static DoctorEntity createExampleDoctorA(final SpecializationEntity specializationEntity) {
        return DoctorEntity.builder()
                .id(1L)
                .name("John Doe")
                .age(35)
                .specializationEntity(specializationEntity)
                .build();
    }

    public static PatientEntity createExamplePatientA() {
        return PatientEntity.builder()
                .id(1L)
                .name("Jane Doe")
                .age(25)
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
