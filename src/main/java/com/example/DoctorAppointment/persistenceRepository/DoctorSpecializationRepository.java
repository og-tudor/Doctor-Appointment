package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
    @Query("SELECT ds FROM DoctorSpecialization ds WHERE ds.doctor = ?1")
    Optional<DoctorSpecialization> findByDoctor(DoctorEntity doctor);

    @Query("SELECT ds FROM DoctorSpecialization ds WHERE ds.id = ?1")
    Optional<DoctorSpecialization> findDSById(Long doctorId);
}
