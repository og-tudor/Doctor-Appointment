package com.example.DoctorAppointment.services;

import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import org.springframework.stereotype.Service;

public interface SpecializationService {
    SpecializationEntity createSpecialization(SpecializationEntity specializationEntity);

    SpecializationEntity getSpecializationById(String id);

    SpecializationEntity deleteSpecializationById(String id);

    Iterable<SpecializationEntity> getAllSpecializations();

    SpecializationEntity updateSpecialization(SpecializationEntity specializationEntity);
}
