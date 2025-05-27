package br.com.vortexlab.VortexLab.plan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.notNullValue;

@DataJpaTest
public class PlanRepositoryTest {

  @Autowired private PlanRepository planRepository;

  @Test
  @DisplayName("Given valid plan, when save, then return saved plan with id")
  void givenValidPlan_whenSave_thenReturnSavedPlanWithId() {
    // Given: set up initial state, input data, and mocked behavior
    // givenValidUserId_whenFindUserById_thenReturnUser
    Plan plan =
        Plan.builder()
            .name("Platinum")
            .description("Platinium III")
            .type(PlanTypeRecorrence.MONTHLY)
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .price(new BigDecimal(99))
            .build();

    // When / Then: execute the method under test and capture the result or exception
    // Example: var result = service.doSomething(id);
    // Or: Exception exception = assertThrows(ExpectedException.class, () ->

    Plan savedPlan = planRepository.save(plan);

    // Then: assert the expected output, exception message, or system state
    // Example: assertThat(result.getName(), is("Expected Name"));

    // Optionally: verify interactions with mocked dependencies
    // Example: verify(repository, times(1)).findById(id);

    assertThat(savedPlan.getId(), notNullValue());
  }
}
