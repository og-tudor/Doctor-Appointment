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
@RequestMapping("/specializations")
public class SpecializationController {
    private SpecializationService specializationService;
//    inject specializationService
    public SpecializationController(SpecializationService specializationService) {
        this.specializationService = specializationService;
    }

    @PostMapping
    public ResponseEntity<SpecializationEntity> createSpecialization(@RequestBody SpecializationEntity specialization) {
        SpecializationEntity savedEntity = specializationService.createSpecialization(specialization);
        log.info("Got specialization : " + savedEntity.toString());
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<SpecializationEntity> getSpecializationById(@PathVariable String id) {
        SpecializationEntity specialization = specializationService.getSpecializationById(id);
        log.info("Got specialization : " + specialization.toString());
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<SpecializationEntity> deleteSpecializationById(@PathVariable String id) {
        SpecializationEntity specialization = specializationService.deleteSpecializationById(id);
        log.info("Deleted specialization : " + specialization.toString());
        return new ResponseEntity<>(specialization, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Iterable<SpecializationEntity>> getAllSpecializations() {
        Iterable<SpecializationEntity> specializations = specializationService.getAllSpecializations();
        log.info("Got all specializations");
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<SpecializationEntity> updateSpecialization(@RequestBody SpecializationEntity specialization) {
        SpecializationEntity updatedEntity = specializationService.updateSpecialization(specialization);
        log.info("Updated specialization : " + updatedEntity.toString());
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
