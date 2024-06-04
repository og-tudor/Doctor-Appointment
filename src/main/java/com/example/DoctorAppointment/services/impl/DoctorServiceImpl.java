package com.example.DoctorAppointment.services.impl;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import com.example.DoctorAppointment.persistenceRepository.DoctorRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorSpecializationRepository;
import com.example.DoctorAppointment.persistenceRepository.SpecializationRepository;
import com.example.DoctorAppointment.services.DoctorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository doctorRepository;
    private final DoctorSpecializationRepository doctorSpecializationRepository;
    private final SpecializationRepository specializationRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository,
                             DoctorSpecializationRepository doctorSpecializationRepository,
                             SpecializationRepository specializationRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorSpecializationRepository = doctorSpecializationRepository;
        this.specializationRepository = specializationRepository;
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

    @Override
    public DoctorEntity addSpecializationToDoctor(Long doctorId, String specializationName) {
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + doctorId));

        SpecializationEntity specialization = specializationRepository.findById(specializationName)
                .orElseThrow(() -> new RuntimeException("Specialization not found with name " + specializationName));

        DoctorSpecialization doctorSpecialization = doctor.getDoctorSpecialization();
//        only create a new DoctorSpecialization if it does not exist
        if (doctorSpecialization == null) {
            doctorSpecialization = new DoctorSpecialization();
            doctorSpecialization.setDoctor(doctor);
            doctor.setDoctorSpecialization(doctorSpecialization);
        }

        doctorSpecialization.addSpecialization(specialization);

        doctorSpecializationRepository.save(doctorSpecialization);
        return doctorRepository.save(doctor);
    }
}
