package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecializationRepository extends CrudRepository<SpecializationEntity, Long> {

}
