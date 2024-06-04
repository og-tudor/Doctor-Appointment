package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorSpecializationRepository extends JpaRepository<DoctorSpecialization, Long> {
}
