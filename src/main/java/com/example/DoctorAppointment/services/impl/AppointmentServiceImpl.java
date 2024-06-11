package com.example.DoctorAppointment.services.impl;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.persistenceRepository.AppointmentRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorRepository;
import com.example.DoctorAppointment.persistenceRepository.PatientRepository;
import com.example.DoctorAppointment.services.AppointmentService;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    AppointmentRepository appointmentRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository,
                                  DoctorRepository doctorRepository,
                                  PatientRepository patientRepository) {
        this.appointmentRepository = appointmentRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public AppointmentEntity createAppointment(AppointmentEntity appointmentEntity, Long doctorId, Long patientId) {
//        find doctor by id
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        find patient by id
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
//        set doctor and patient
        appointmentEntity.setDoctorEntity(doctor);
        appointmentEntity.setPatientEntity(patient);
//        save appointment
        return appointmentRepository.save(appointmentEntity);
    }

    @Override
    public AppointmentEntity getAppointmentById(Long appointmentId) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        return appointmentEntity;
    }

    @Override
    public AppointmentEntity deleteAppointmentById(Long appointmentId) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
        appointmentRepository.delete(appointmentEntity);
        return appointmentEntity;
    }

    @Override
    public Iterable<AppointmentEntity> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Iterable<AppointmentEntity> getAppointmentsByDoctorId(Long doctorId) {
        Iterable<AppointmentEntity> appointmentEntities = appointmentRepository.findAllByDoctorEntityId(doctorId);
        return appointmentEntities;
    }

    @Override
    public Iterable<AppointmentEntity> getAppointmentsByPatientId(Long patientId) {
        Iterable<AppointmentEntity> appointmentEntities = appointmentRepository.findAllByPatientEntityId(patientId);
        return appointmentEntities;
    }

    @Override
    public AppointmentEntity updateAppointment(AppointmentEntity appointmentEntity) {
        return appointmentRepository.save(appointmentEntity);
    }

    @Override
    public AppointmentEntity createAppointment(AppointmentEntity appointmentEntity, SpecializationEntity specializationEntity) {
//        the patient is already set in the appointment entity
//        the doctor is not, need to find an available doctor with the specialization in that day
//        DoctorEntity doctor = doctorRepository.findDoctorBySpecialization(specializationEntity.getId())
//                .orElseThrow(() -> new RuntimeException("Doctor not found"));
//        appointmentEntity.setDoctorEntity(doctor);
//        return appointmentRepository.save(appointmentEntity);
        return null;
    }
}
