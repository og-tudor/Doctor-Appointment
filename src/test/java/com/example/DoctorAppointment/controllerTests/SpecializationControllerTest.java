package com.example.DoctorAppointment.controllers;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.services.SpecializationService;
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

@WebMvcTest(SpecializationController.class)
public class SpecializationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SpecializationService specializationService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testCreateSpecialization() throws Exception {
        SpecializationEntity specialization = TestDataUtil.createExampleSpecializationA();

        when(specializationService.createSpecialization(any(SpecializationEntity.class))).thenReturn(specialization);

        mockMvc.perform(post("/specializations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Cardiology\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value(specialization.getName()));
    }

    @Test
    public void testGetSpecializationById() throws Exception {
        SpecializationEntity specialization = TestDataUtil.createExampleSpecializationA();

        when(specializationService.getSpecializationById("Cardiology")).thenReturn(specialization);

        mockMvc.perform(get("/specializations/{id}", "Cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(specialization.getName()));
    }

    @Test
    public void testDeleteSpecializationById() throws Exception {
        SpecializationEntity specialization = TestDataUtil.createExampleSpecializationA();

        when(specializationService.deleteSpecializationById("Cardiology")).thenReturn(specialization);

        mockMvc.perform(delete("/specializations/{id}", "Cardiology"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(specialization.getName()));
    }

    @Test
    public void testGetAllSpecializations() throws Exception {
        SpecializationEntity specializationA = TestDataUtil.createExampleSpecializationA();
        SpecializationEntity specializationB = TestDataUtil.createExampleSpecializationB();
        SpecializationEntity specializationC = TestDataUtil.createExampleSpecializationC();

        when(specializationService.getAllSpecializations()).thenReturn(Arrays.asList(specializationA, specializationB, specializationC));

        mockMvc.perform(get("/specializations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value(specializationA.getName()))
                .andExpect(jsonPath("$[1].name").value(specializationB.getName()))
                .andExpect(jsonPath("$[2].name").value(specializationC.getName()));
    }

    @Test
    public void testUpdateSpecialization() throws Exception {
        SpecializationEntity specialization = TestDataUtil.createExampleSpecializationA();

        when(specializationService.updateSpecialization(any(SpecializationEntity.class))).thenReturn(specialization);

        mockMvc.perform(put("/specializations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Cardiology\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(specialization.getName()));
    }
}
