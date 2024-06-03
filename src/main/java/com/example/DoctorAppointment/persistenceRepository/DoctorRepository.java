package com.example.DoctorAppointment.persistenceRepository;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends CrudRepository<DoctorEntity, Long> {

}
