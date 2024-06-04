package com.example.DoctorAppointment.domain.entities.intermediary;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "doctor_specialization")
public class DoctorSpecialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @OneToMany(mappedBy = "doctorSpecialization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SpecializationEntity> specializations = new HashSet<>();

    public void addSpecialization(SpecializationEntity specialization) {
        specializations.add(specialization);
        specialization.setDoctorSpecialization(this);
    }
}
