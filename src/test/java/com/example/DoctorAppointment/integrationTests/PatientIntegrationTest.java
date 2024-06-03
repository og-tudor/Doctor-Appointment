package com.example.DoctorAppointment.integrationTests;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.PatientEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.persistenceRepository.PatientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.Optional;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PatientIntegrationTest {
    private PatientRepository patientRepository;

    @Autowired
    public PatientIntegrationTest(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Test
    public void makeSureTheDatabaseRuns() {
        assertThat(true).isTrue();
    }

    @Test
    @Transactional
    public void testThatPatientCanBeCreatedAndRecalled() {
        PatientEntity patient = TestDataUtil.createExamplePatientA();
        patientRepository.save(patient);
        Optional<PatientEntity> result = patientRepository.findById(patient.getId());
        assert(result).isPresent();
        assertThat(result.get()).isEqualTo(patient);
        System.out.println("PatientEntity: " + result.get());
    }


}
