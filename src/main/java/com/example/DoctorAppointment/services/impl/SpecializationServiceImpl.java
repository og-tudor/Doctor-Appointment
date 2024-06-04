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
    public SpecializationEntity getSpecializationById(String id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found with id " + id));
    }

    @Override
    public SpecializationEntity deleteSpecializationById(String id) {
        SpecializationEntity specializationEntity = specializationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Specialization not found with id " + id));
        specializationRepository.delete(specializationEntity);
        return specializationEntity;
    }

    @Override
    public Iterable<SpecializationEntity> getAllSpecializations() {
        return specializationRepository.findAll();
    }

    @Override
    public SpecializationEntity updateSpecialization(SpecializationEntity specializationEntity) {
        return specializationRepository.save(specializationEntity);
    }


}
