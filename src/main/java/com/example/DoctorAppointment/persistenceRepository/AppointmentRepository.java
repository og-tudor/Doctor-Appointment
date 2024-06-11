package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    @Query("SELECT a FROM AppointmentEntity a WHERE a.doctorEntity.id = ?1")
    Iterable<AppointmentEntity> findAllByDoctorEntityId(Long doctorId);

    @Query("SELECT A FROM AppointmentEntity A WHERE A.patientEntity.id = ?1")
    Iterable<AppointmentEntity> findAllByPatientEntityId(Long patientId);

    // Check if doctor is available
    @Query("SELECT a FROM AppointmentEntity a WHERE a.doctorEntity.id = ?1 AND a.date = ?2")
    Iterable<AppointmentEntity> findDoctorAvailability(Long doctorId, LocalDateTime appointmentDate);
}
