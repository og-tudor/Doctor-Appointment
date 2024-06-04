package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.services.impl.AppointmentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

public class AppointmentControllerTest {

    @Mock
    private AppointmentServiceImpl appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    private AppointmentEntity exampleAppointment;
    private AppointmentEntity exampleAppointmentB;
    private AppointmentEntity exampleAppointmentC;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        exampleAppointment = TestDataUtil.createExampleAppointmentA();
        exampleAppointmentB = TestDataUtil.createExampleAppointmentA();
        exampleAppointmentC = TestDataUtil.createExampleAppointmentA();
    }

    @Test
    public void testCreateAppointment() {
        when(appointmentService.createAppointment(any(AppointmentEntity.class), anyLong(), anyLong()))
                .thenReturn(exampleAppointment);

        ResponseEntity<AppointmentEntity> response = appointmentController.createAppointment(exampleAppointment, 1L, 1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isEqualTo(exampleAppointment);
        verify(appointmentService, times(1)).createAppointment(any(AppointmentEntity.class), anyLong(), anyLong());
    }

    @Test
    public void testGetAppointmentById() {
        when(appointmentService.getAppointmentById(anyLong())).thenReturn(exampleAppointment);

        ResponseEntity<AppointmentEntity> response = appointmentController.getAppointmentById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(exampleAppointment);
        verify(appointmentService, times(1)).getAppointmentById(anyLong());
    }

    @Test
    public void testDeleteAppointmentById() {
        when(appointmentService.deleteAppointmentById(anyLong())).thenReturn(exampleAppointment);

        ResponseEntity<Void> response = appointmentController.deleteAppointmentById(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        verify(appointmentService, times(1)).deleteAppointmentById(anyLong());
    }

    @Test
    public void testGetAllAppointments() {
        when(appointmentService.getAllAppointments()).thenReturn(Arrays.asList(exampleAppointment, exampleAppointmentB, exampleAppointmentC));

        ResponseEntity<Iterable<AppointmentEntity>> response = appointmentController.getAllAppointments();

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyInAnyOrder(exampleAppointment, exampleAppointmentB, exampleAppointmentC);
        verify(appointmentService, times(1)).getAllAppointments();
    }

    @Test
    public void testUpdateAppointment() {
        when(appointmentService.updateAppointment(any(AppointmentEntity.class))).thenReturn(exampleAppointment);

        ResponseEntity<AppointmentEntity> response = appointmentController.updateAppointment(exampleAppointment);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo(exampleAppointment);
        verify(appointmentService, times(1)).updateAppointment(any(AppointmentEntity.class));
    }

    @Test
    public void testGetAppointmentsByDoctorId() {
        when(appointmentService.getAppointmentsByDoctorId(anyLong())).thenReturn(Arrays.asList(exampleAppointment, exampleAppointmentB));

        ResponseEntity<Iterable<AppointmentEntity>> response = appointmentController.getAppointmentsByDoctorId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyInAnyOrder(exampleAppointment, exampleAppointmentB);
        verify(appointmentService, times(1)).getAppointmentsByDoctorId(anyLong());
    }

    @Test
    public void testGetAppointmentsByPatientId() {
        when(appointmentService.getAppointmentsByPatientId(anyLong())).thenReturn(Arrays.asList(exampleAppointment, exampleAppointmentC));

        ResponseEntity<Iterable<AppointmentEntity>> response = appointmentController.getAppointmentsByPatientId(1L);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).containsExactlyInAnyOrder(exampleAppointment, exampleAppointmentC);
        verify(appointmentService, times(1)).getAppointmentsByPatientId(anyLong());
    }

    @Test
    public void testHandleRuntimeException() {
        RuntimeException exception = new RuntimeException("Test Exception");
        ResponseEntity<String> response = appointmentController.handleRuntimeException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isEqualTo("Test Exception");
    }
}
