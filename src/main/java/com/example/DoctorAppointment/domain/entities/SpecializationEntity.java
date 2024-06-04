package com.example.DoctorAppointment.domain.entities;

import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "specializations")
public class SpecializationEntity {
    @Id
    private String name;

    @ManyToMany(mappedBy = "specializations")
    @JsonIgnoreProperties("specializations")
    private Set<DoctorSpecialization> doctorSpecializations = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SpecializationEntity that = (SpecializationEntity) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}


