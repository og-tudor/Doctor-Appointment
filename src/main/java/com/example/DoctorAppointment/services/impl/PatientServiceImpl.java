package com.example.DoctorAppointment.services.impl;

import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.persistenceRepository.PatientRepository;
import com.example.DoctorAppointment.services.PatientService;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
    PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public PatientEntity createPatient(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    @Override
    public PatientEntity getPatientById(Long patientId) {
        return patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));
    }

    @Override
    public PatientEntity deletePatientById(Long patientId) {
        PatientEntity patientEntity = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + patientId));
        patientRepository.delete(patientEntity);
        return patientEntity;
    }

    @Override
    public Iterable<PatientEntity> getAllPatients() {
        Iterable<PatientEntity> patientEntities = patientRepository.findAll();
        return patientEntities;
    }

    @Override
    public PatientEntity updatePatient(Long patientId, PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }
}
