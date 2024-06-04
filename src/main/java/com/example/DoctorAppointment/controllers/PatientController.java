package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.services.PatientService;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping(path = "/patients")
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient) {
        log.info("Got specialization : " + patient.toString());
        PatientEntity savedEntity = patientService.createPatient(patient);
        ResponseEntity<PatientEntity> response = new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        return response;
    }
}
