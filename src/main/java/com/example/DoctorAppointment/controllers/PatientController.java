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
@RequestMapping("/patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient) {
        log.info("Got specialization : " + patient.toString());
        PatientEntity savedEntity = patientService.createPatient(patient);
        ResponseEntity<PatientEntity> response = new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        return response;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable Long id) {
        PatientEntity patient = patientService.getPatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<PatientEntity> deletePatientById(@PathVariable Long id) {
        PatientEntity patient = patientService.deletePatientById(id);
        return new ResponseEntity<>(patient, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<PatientEntity>> getAllPatients() {
        Iterable<PatientEntity> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable Long id, @RequestBody PatientEntity patient) {
        PatientEntity updatedEntity = patientService.updatePatient(id, patient);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }
}

