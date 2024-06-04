package com.example.DoctorAppointment.domain.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "appointments")
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_id")
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime date;
    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonIgnore
    private PatientEntity patientEntity;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    @JsonIgnore
    private DoctorEntity doctorEntity;
}
