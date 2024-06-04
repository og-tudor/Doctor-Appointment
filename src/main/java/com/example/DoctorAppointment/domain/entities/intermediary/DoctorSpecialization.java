package com.example.DoctorAppointment.domain.entities.intermediary;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctor_specializations")
public class DoctorSpecialization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonBackReference
    private DoctorEntity doctor;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialization_specializations",
            joinColumns = @JoinColumn(name = "doctor_specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_name")
    )
    private Set<SpecializationEntity> specializations = new HashSet<>();

    public void addSpecialization(SpecializationEntity specialization) {
        specializations.add(specialization);
        if (specialization.getDoctorSpecializations() == null) {
            specialization.setDoctorSpecializations(new HashSet<>());
        }
        specialization.getDoctorSpecializations().add(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorSpecialization that = (DoctorSpecialization) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


