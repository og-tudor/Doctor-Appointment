package com.example.DoctorAppointment.services;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;

public interface AppointmentService {
    public AppointmentEntity createAppointment(AppointmentEntity appointmentEntity, Long doctorId, Long patientId);

    AppointmentEntity getAppointmentById(Long appointmentId);

    AppointmentEntity deleteAppointmentById(Long appointmentId);

    Iterable<AppointmentEntity> getAllAppointments();

    Iterable<AppointmentEntity> getAppointmentsByDoctorId(Long doctorId);

    Iterable<AppointmentEntity> getAppointmentsByPatientId(Long patientId);

    AppointmentEntity updateAppointment(AppointmentEntity appointmentEntity);

    AppointmentEntity createAppointment(AppointmentEntity appointmentEntity, SpecializationEntity specializationEntity);
}
