package br.com.vortexlab.VortexLab.plan;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

  @Mock private PlanRepository planRepository;
  @Mock private PlanMapper planMapper;
  @InjectMocks private PlanService planService;

  @Test
  @DisplayName("Given valid plan request, when register plan, then returns plan response")
  void givenValidPlanRequest_whenRegisterPlan_thenReturnPlanResponse() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2024, 1, 1, 10, 0);

    var request =
        new PlanRequest(
            new PlanBase(
                "Platinium",
                "something...",
                PlanTypeRecorrence.MONTHLY,
                new BigDecimal("10.00"),
                "support 24h, 7 days on week; backup; 1000 emails"));

    var plan =
        Plan.builder()
            .name("Platinium")
            .description("something...")
            .type(PlanTypeRecorrence.MONTHLY)
            .price(new BigDecimal("10.00"))
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .build();

    var planSaved =
        Plan.builder()
            .name("Platinium")
            .description("something...")
            .type(PlanTypeRecorrence.MONTHLY)
            .price(new BigDecimal("10.00"))
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .build();
    planSaved.setId(1L);
    planSaved.setCreatedAt(fixedTime);
    planSaved.setUpdatedAt(fixedTime);

    var expectedResponse =
        new PlanResponse(
            new PlanBase(
                "Platinium",
                "something...",
                PlanTypeRecorrence.MONTHLY,
                new BigDecimal("10.00"),
                "support 24h, 7 days on week; backup; 1000 emails"),
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    // When / Act: call the method under test
    when(planMapper.toEntity(request)).thenReturn(plan);
    when(planRepository.save(plan)).thenReturn(planSaved);
    when(planMapper.toResponse(planSaved)).thenReturn(expectedResponse);

    PlanResponse actualResponse = planService.registerPlan(request);

    // Then / Assert: verify results
    assertThat(actualResponse, equalTo(expectedResponse));
    assertThat(actualResponse.id(), equalTo(expectedResponse.id()));
    assertThat(actualResponse.deleted(), is(false));

    // Verify mock interactions
    verify(planMapper, times(1)).toEntity(request);
    verify(planRepository, times(1)).save(plan);
    verify(planMapper, times(1)).toResponse(planSaved);
  }
}
