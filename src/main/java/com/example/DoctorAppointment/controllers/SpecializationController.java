package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.services.impl.SpecializationServiceImpl;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.DoctorAppointment.services.SpecializationService;

@RestController
@Log
public class SpecializationController {
    private SpecializationService specializationService;
//    inject specializationService
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping(path = "/specializations")
    public ResponseEntity<SpecializationEntity> createSpecialization(@RequestBody SpecializationEntity specialization) {
        log.info("Got specialization : " + specialization.toString());
        SpecializationEntity savedEntity = specializationService.createSpecialization(specialization);
        ResponseEntity<SpecializationEntity> response = new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        return response;
    }

    @PostMapping(path = "/specializations/{specializationId}/doctors/{doctorId}")
    public ResponseEntity<SpecializationEntity> addDoctorToSpecialization(@RequestBody SpecializationEntity specialization,
                                                                          @PathVariable("specializationId") String specializationId,
                                                                          @PathVariable("doctorId") Long doctorId) {
        log.info("Got specialization : " + specialization.toString());
        SpecializationEntity savedEntity = specializationService.addDoctorToSpecialization(specializationId, doctorId);
        ResponseEntity<SpecializationEntity> response = new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}