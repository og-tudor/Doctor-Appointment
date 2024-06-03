package com.example.DoctorAppointment.integrationTests;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.AppointmentEntity;
import com.example.DoctorAppointment.domain.entities.DoctorEntity;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.persistenceRepository.AppointmentRepository;
import com.example.DoctorAppointment.persistenceRepository.DoctorRepository;
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
public class AppointmentIntegrationTest {
    private AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentIntegrationTest(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Test
    public void makeSureTheDatabaseRuns() {
        assertThat(true).isTrue();
    }

    @Test
    @Transactional
    public void testThatAppointmentCanBeCreatedAndRecalled() {
        AppointmentEntity appointment = TestDataUtil.createExampleAppointmentA();
        appointmentRepository.save(appointment);
        Optional<AppointmentEntity> result = appointmentRepository.findById(appointment
                .getId());
        assert(result).isPresent();
        assertThat(result.get()).isEqualTo(appointment);
        System.out.println("AppointmentEntity: " + result.get());
    }
}
