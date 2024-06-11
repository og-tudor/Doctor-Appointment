package com.example.DoctorAppointment.services.impl;

import com.example.DoctorAppointment.domain.dto.DoctorDto;
import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import com.example.DoctorAppointment.persistenceRepository.AppointmentRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorSpecializationRepository;
import com.example.DoctorAppointment.persistenceRepository.SpecializationRepository;
import com.example.DoctorAppointment.services.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final SpecializationRepository specializationRepository;
    private AppointmentRepository appointmentRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             DoctorSpecializationRepository doctorSpecializationRepository,
                             SpecializationRepository specializationRepository,
                             AppointmentRepository appointmentRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.specializationRepository = specializationRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    @Transactional
    public DoctorEntity createDoctor(DoctorEntity doctorEntity) {
        return doctorRepository.save(doctorEntity);
    }

    @Override
    public DoctorEntity getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));
    }

    @Override
    public DoctorEntity deleteDoctorById(Long doctorId) {
        DoctorEntity doctorEntity = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));
        doctorRepository.delete(doctorEntity);
        return doctorEntity;
    }

    @Override
    public Iterable<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Transactional
    @Override
    public DoctorEntity addSpecializationToDoctor(Long doctorId, String specializationName) {
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));

        SpecializationEntity specialization = specializationRepository.findById(specializationName)
                .orElseThrow(() -> new RuntimeException("Specialization not found with name " + specializationName));

        DoctorSpecialization doctorSpecialization = doctorSpecializationRepository.findDSById(doctorId)
                .orElse(new DoctorSpecialization());

        if (doctorSpecialization.getDoctor() == null) {
            doctorSpecialization.setDoctor(doctor);
            doctor.getDoctorSpecializations().add(doctorSpecialization);
        }

        doctorSpecialization.addSpecialization(specialization);

        doctorSpecializationRepository.save(doctorSpecialization);
        return doctorRepository.save(doctor);
    }

    @Override
    public Iterable<SpecializationEntity> getDoctorSpecialization(Long doctorId) {
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));

        Set<SpecializationEntity> specializations = doctorSpecializationRepository.findDSById(doctorId)
                .map(DoctorSpecialization::getSpecializations)
                .orElseThrow(() -> new RuntimeException("Doctor Specialization not found with doctor id " + doctorId));

        return specializations;
    }

    public Iterable<DoctorDto> findAvailableDoctorsBySpecializationAndDate(String specializationId, LocalDateTime appointmentDate) {
        // Find doctors by specialization
        Iterable<DoctorEntity> doctorEntities = doctorSpecializationRepository.findDoctorsBySpecialization(specializationId);

        // Convert DoctorEntity to DoctorDTO
        Set<DoctorDto> doctorDTOs = new HashSet<>();
        for (DoctorEntity doctorEntity : doctorEntities) {
            doctorDTOs.add(new DoctorDto(doctorEntity.getId(), doctorEntity.getName(), doctorEntity.getAge()));
        }

        // Filter available doctors
        Set<DoctorDto> availableDoctors = new HashSet<>();
        for (DoctorDto doctor : doctorDTOs) {
            Iterable<AppointmentEntity> appointments = appointmentRepository.findDoctorAvailability(doctor.getId(), appointmentDate);
            if (!appointments.iterator().hasNext()) { // No appointments at this time, doctor is available
                availableDoctors.add(doctor);
            }
        }

        return availableDoctors;
    }
}
