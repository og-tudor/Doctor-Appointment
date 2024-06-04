package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.services.DoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctors")
@Slf4j
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @PostMapping
    public ResponseEntity<DoctorEntity> createDoctor(@RequestBody DoctorEntity doctor) {
        log.info("Got doctor: {}", doctor);
        DoctorEntity savedEntity = doctorService.createDoctor(doctor);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @PostMapping("/{doctorId}/specializations/{specializationName}")
    public ResponseEntity<DoctorEntity> addSpecializationToDoctor(@PathVariable Long doctorId,
                                                                  @PathVariable String specializationName) {
        log.info("Adding specialization {} to doctor with id {}", specializationName, doctorId);
        DoctorEntity updatedDoctor = doctorService.addSpecializationToDoctor(doctorId, specializationName);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
