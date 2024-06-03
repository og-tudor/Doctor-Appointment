package com.example.DoctorAppointment.integrationTests;

import com.example.DoctorAppointment.TestDataUtil;
import com.example.DoctorAppointment.domain.entities.SpecializationEntity;
import com.example.DoctorAppointment.persistenceRepository.SpecializationRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Optional;


import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class SpecializationIntegrationTest {
    private SpecializationRepository specializationRepository;

    @Autowired
    public SpecializationIntegrationTest(SpecializationRepository specializationRepository) {
        this.specializationRepository = specializationRepository;
    }

    @Test
    public void makeSureTheDatabaseRuns() {
        assertThat(true).isTrue();
    }

    @Test
    @Transactional
    public void testThatSpecializationCanBeCreatedAndRecalled() {
        SpecializationEntity specializationEntity = TestDataUtil.createExampleSpecializationA();
        specializationRepository.save(specializationEntity);
        Optional<SpecializationEntity> result = specializationRepository.findById(specializationEntity.getName());
        assert (result).isPresent();
        assertThat(result.get()).isEqualTo(specializationEntity);
        System.out.println("SpecializationEntity: " + result.get());
    }

    @Test
    @Transactional
    public void testThatMultipleSpecializationsCanBeCreatedAndRecalled() {
        SpecializationEntity specializationEntityA = TestDataUtil.createExampleSpecializationA();
        specializationRepository.save(specializationEntityA);
        SpecializationEntity specializationEntityB = TestDataUtil.createExampleSpecializationB();
        specializationRepository.save(specializationEntityB);
        SpecializationEntity specializationEntityC = TestDataUtil.createExampleSpecializationC();
        specializationRepository.save(specializationEntityC);

        Iterable<SpecializationEntity> results = specializationRepository.findAll();
        assertThat(results).hasSize(3).containsExactly(specializationEntityA, specializationEntityB, specializationEntityC);
    }

    @Test
    @Transactional
    public void testThatSpecializationCanBeDeleted() {
        SpecializationEntity specializationEntity = TestDataUtil.createExampleSpecializationA();
        specializationRepository.save(specializationEntity);
        specializationRepository.delete(specializationEntity);
        Optional<SpecializationEntity> result = specializationRepository.findById(specializationEntity.getName());
        assert(result).isEmpty();
    }


}