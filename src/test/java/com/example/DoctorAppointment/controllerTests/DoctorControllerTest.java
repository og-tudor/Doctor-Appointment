package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.domain.entities.intermediary.DoctorSpecialization;
import com.example.DoctorAppointment.services.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(DoctorController.class)
public class DoctorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DoctorService doctorService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testCreateDoctor() throws Exception {
        DoctorEntity doctor = TestDataUtil.createExampleDoctorA();

        when(doctorService.createDoctor(any(DoctorEntity.class))).thenReturn(doctor);

        mockMvc.perform(post("/doctors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"John Doe\", \"age\": 40}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(doctor.getId()))
                .andExpect(jsonPath("$.name").value(doctor.getName()))
                .andExpect(jsonPath("$.age").value(doctor.getAge()));
    }

    @Test
    public void testGetDoctorById() throws Exception {
        DoctorEntity doctor = TestDataUtil.createExampleDoctorA();

        when(doctorService.getDoctorById(1L)).thenReturn(doctor);

        mockMvc.perform(get("/doctors/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(doctor.getId()))
                .andExpect(jsonPath("$.name").value(doctor.getName()))
                .andExpect(jsonPath("$.age").value(doctor.getAge()));
    }

    @Test
    public void testDeleteDoctorById() throws Exception {
        DoctorEntity doctor = TestDataUtil.createExampleDoctorA();

        when(doctorService.deleteDoctorById(1L)).thenReturn(doctor);

        mockMvc.perform(delete("/doctors/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void testGetAllDoctors() throws Exception {
        DoctorEntity doctorA = TestDataUtil.createExampleDoctorA();
        DoctorEntity doctorB = TestDataUtil.createExampleDoctorB();
        DoctorEntity doctorC = TestDataUtil.createExampleDoctorC();

        when(doctorService.getAllDoctors()).thenReturn(Arrays.asList(doctorA, doctorB, doctorC));

        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(doctorA.getId()))
                .andExpect(jsonPath("$[0].name").value(doctorA.getName()))
                .andExpect(jsonPath("$[0].age").value(doctorA.getAge()))
                .andExpect(jsonPath("$[1].id").value(doctorB.getId()))
                .andExpect(jsonPath("$[1].name").value(doctorB.getName()))
                .andExpect(jsonPath("$[1].age").value(doctorB.getAge()))
                .andExpect(jsonPath("$[2].id").value(doctorC.getId()))
                .andExpect(jsonPath("$[2].name").value(doctorC.getName()))
                .andExpect(jsonPath("$[2].age").value(doctorC.getAge()));
    }

    @Test
    public void testAddSpecializationToDoctor() throws Exception {
        DoctorEntity doctor = TestDataUtil.createExampleDoctorA();
        doctor.setId(1L);

        SpecializationEntity specialization = TestDataUtil.createExampleSpecializationA();
        specialization.setName("Dentist");

        DoctorSpecialization doctorSpecialization = new DoctorSpecialization();
        doctorSpecialization.setId(1L);
        doctorSpecialization.setDoctor(doctor);
        doctorSpecialization.addSpecialization(specialization); // This line should work now since the set is initialized.
        doctor.getDoctorSpecializations().add(doctorSpecialization);

        when(doctorService.addSpecializationToDoctor(eq(1L), eq("Dentist"))).thenReturn(doctor);

        mockMvc.perform(post("/doctors/{doctorId}/specializations/{specializationName}", 1L, "Dentist"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(doctor.getId()))
                .andExpect(jsonPath("$.name").value(doctor.getName()))
                .andExpect(jsonPath("$.age").value(doctor.getAge()))
                .andExpect(jsonPath("$.doctorSpecializations[0].id").value(doctorSpecialization.getId()))
                .andExpect(jsonPath("$.doctorSpecializations[0].specializations[0].name").value(specialization.getName()));
    }


}
