package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.services.impl.AppointmentServiceImpl;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;

@RestController
@RequestMapping("/appointments")
@Log
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(path = "/doctors/{doctorId}/patients/{patientId}")
    public ResponseEntity<AppointmentEntity> createDoctor(@RequestBody AppointmentEntity appointmentEntity,
                                                          @PathVariable Long doctorId,
                                                          @PathVariable Long patientId) {
        AppointmentEntity savedEntity = appointmentService.createAppointment(appointmentEntity, doctorId, patientId);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
