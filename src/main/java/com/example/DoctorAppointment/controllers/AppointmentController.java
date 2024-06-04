package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.services.impl.AppointmentServiceImpl;
import lombok.extern.java.Log;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
@Log
public class AppointmentController {
    private final AppointmentServiceImpl appointmentService;

    public AppointmentController(AppointmentServiceImpl appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping(path = "/doctors/{doctorId}/patients/{patientId}")
    public ResponseEntity<AppointmentEntity> createAppointment(@RequestBody AppointmentEntity appointmentEntity,
                                                               @PathVariable Long doctorId,
                                                               @PathVariable Long patientId) {
        AppointmentEntity savedEntity = appointmentService.createAppointment(appointmentEntity, doctorId, patientId);
        return new ResponseEntity<>(savedEntity, HttpStatus.CREATED);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AppointmentEntity> getAppointmentById(@PathVariable Long id) {
        AppointmentEntity appointment = appointmentService.getAppointmentById(id);
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteAppointmentById(@PathVariable Long id) {
        appointmentService.deleteAppointmentById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<Iterable<AppointmentEntity>> getAllAppointments() {
        Iterable<AppointmentEntity> appointments = appointmentService.getAllAppointments();
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<AppointmentEntity> updateAppointment(@RequestBody AppointmentEntity appointment) {
        AppointmentEntity updatedEntity = appointmentService.updateAppointment(appointment);
        return new ResponseEntity<>(updatedEntity, HttpStatus.OK);
    }

    @GetMapping(path = "/doctor/{doctorId}")
    public ResponseEntity<Iterable<AppointmentEntity>> getAppointmentsByDoctorId(@PathVariable Long doctorId) {
        Iterable<AppointmentEntity> appointments = appointmentService.getAppointmentsByDoctorId(doctorId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @GetMapping(path = "/patient/{patientId}")
    public ResponseEntity<Iterable<AppointmentEntity>> getAppointmentsByPatientId(@PathVariable Long patientId) {
        Iterable<AppointmentEntity> appointments = appointmentService.getAppointmentsByPatientId(patientId);
        return new ResponseEntity<>(appointments, HttpStatus.OK);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
