package com.example.DoctorAppointment;

import com.example.DoctorAppointment.domain.entities.SpecializationEntity;

public class TestDataUtil {
    public TestDataUtil() {
    }

    public static SpecializationEntity createExampleSpecializationA() {
        return SpecializationEntity.builder()
                .id(1L)
                .name("Cardiology")
                .doctorEntities(null)
                .build();
    }

}
