package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.services.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PatientController.class)
public class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientServiceImpl patientService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testCreatePatient() throws Exception {
        PatientEntity patient = TestDataUtil.createExamplePatientA();

        when(patientService.createPatient(any(PatientEntity.class))).thenReturn(patient);

        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"lastName\": \"Doe\", \"surName\": \"John\", \"email\": \"random_email@gmail.com\", \"phoneNumber\": \"1234567890\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()))
                .andExpect(jsonPath("$.surName").value(patient.getSurName()))
                .andExpect(jsonPath("$.email").value(patient.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(patient.getPhoneNumber()));
    }

    @Test
    public void testGetPatientById() throws Exception {
        PatientEntity patient = TestDataUtil.createExamplePatientA();

        when(patientService.getPatientById(1L)).thenReturn(patient);

        mockMvc.perform(get("/patients/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(patient.getId()))
                .andExpect(jsonPath("$.lastName").value(patient.getLastName()))
                .andExpect(jsonPath("$.surName").value(patient.getSurName()))
                .andExpect(jsonPath("$.email").value(patient.getEmail()))
                .andExpect(jsonPath("$.phoneNumber").value(patient.getPhoneNumber()));
    }


    @Test
    public void testDeletePatient() throws Exception {
        mockMvc.perform(delete("/patients/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetPatients() throws Exception {
        PatientEntity patientA = TestDataUtil.createExamplePatientA();
        PatientEntity patientB = TestDataUtil.createExamplePatientB();

        List<PatientEntity> patients = Arrays.asList(patientA, patientB);

        when(patientService.getAllPatients()).thenReturn(patients);

        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(patientA.getId()))
                .andExpect(jsonPath("$[0].lastName").value(patientA.getLastName()))
                .andExpect(jsonPath("$[0].surName").value(patientA.getSurName()))
                .andExpect(jsonPath("$[0].email").value(patientA.getEmail()))
                .andExpect(jsonPath("$[0].phoneNumber").value(patientA.getPhoneNumber()))
                .andExpect(jsonPath("$[1].id").value(patientB.getId()))
                .andExpect(jsonPath("$[1].lastName").value(patientB.getLastName()))
                .andExpect(jsonPath("$[1].surName").value(patientB.getSurName()))
                .andExpect(jsonPath("$[1].email").value(patientB.getEmail()))
                .andExpect(jsonPath("$[1].phoneNumber").value(patientB.getPhoneNumber()));

    }
}
