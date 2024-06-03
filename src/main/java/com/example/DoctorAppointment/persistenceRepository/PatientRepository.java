package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.PatientEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<PatientEntity, Long> {

}
