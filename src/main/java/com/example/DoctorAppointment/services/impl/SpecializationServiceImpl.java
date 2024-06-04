package com.example.DoctorAppointment.services.impl;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.persistenceRepository.SpecializationRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorRepository;
import com.example.DoctorAppointment.services.SpecializationService;

import org.springframework.stereotype.Service;

@Service
public class SpecializationServiceImpl implements SpecializationService {
    private SpecializationRepository specializationRepository;
    private DoctorRepository doctorRepository;
//    inject specializationRepository
    public SpecializationServiceImpl(SpecializationRepository specializationRepository, DoctorRepository doctorRepository) {
        this.specializationRepository = specializationRepository;
        this.doctorRepository = doctorRepository;
    }

//    createSpecialization method
    public SpecializationEntity createSpecialization(SpecializationEntity specializationEntity) {
        return specializationRepository.save(specializationEntity);
    }

    @Override
    public SpecializationEntity addDoctorToSpecialization(String specializationId, Long doctorId) {
        SpecializationEntity specializationEntity = specializationRepository.findById(specializationId)
                .orElseThrow(() -> new RuntimeException("Specialization not found with id " + specializationId));
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));
//        specializationEntity.getDoctorEntities().add(doctorEntity);
        return specializationRepository.save(specializationEntity);
    }
}
