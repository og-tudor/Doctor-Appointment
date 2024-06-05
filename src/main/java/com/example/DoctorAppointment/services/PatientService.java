package com.example.DoctorAppointment.services;

import com.example.DoctorAppointment.domain.entities.PatientEntity;

public interface PatientService {
    PatientEntity createPatient(PatientEntity patientEntity);

    PatientEntity getPatientById(Long patientId);

    PatientEntity deletePatientById(Long patientId);

    Iterable<PatientEntity> getAllPatients();

    PatientEntity updatePatient(Long patientId, PatientEntity patientEntity);
}
