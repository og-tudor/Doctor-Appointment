package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.services.DoctorService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@Log
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorEntity> createDoctor(@RequestBody DoctorEntity doctor) {
        log.info("Got doctor : " + doctor.toString());
        DoctorEntity savedEntity = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable Long id) {
        DoctorEntity doctor = doctorService.getDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<DoctorEntity> deleteDoctorById(@PathVariable Long id) {
        DoctorEntity doctor = doctorService.deleteDoctorById(id);
        return new ResponseEntity<>(doctor, HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<DoctorEntity>> getAllDoctors() {
        Iterable<DoctorEntity> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PostMapping("/{doctorId}/specializations/{specializationName}")
    public ResponseEntity<DoctorEntity> addSpecializationToDoctor(@PathVariable Long doctorId,
                                                                  @PathVariable String specializationName) {
        log.info("Got doctor id : " + doctorId + " and specialization name : " + specializationName);
        DoctorEntity updatedDoctor = doctorService.addSpecializationToDoctor(doctorId, specializationName);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @GetMapping("/{doctorId}/specializations")
    public ResponseEntity<Iterable<SpecializationEntity>> getDoctorSpecialization(@PathVariable Long doctorId) {
        Iterable<SpecializationEntity> specializations = doctorService.getDoctorSpecialization(doctorId);
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
