package com.example.DoctorAppointment.services;

import com.example.DoctorAppointment.domain.dto.DoctorDto;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;

import java.time.LocalDateTime;

public interface DoctorService {
    public DoctorEntity createDoctor(DoctorEntity doctorEntity);

    DoctorEntity getDoctorById(Long doctorId);

    DoctorEntity deleteDoctorById(Long doctorId);

    Iterable<DoctorEntity> getAllDoctors();

    DoctorEntity addSpecializationToDoctor(Long doctorId, String specializationName);

//    get doctor specialization
    Iterable<SpecializationEntity> getDoctorSpecialization(Long doctorId);

    Iterable<DoctorDto> findAvailableDoctorsBySpecializationAndDate(String specializationId, LocalDateTime appointmentDate);
}
