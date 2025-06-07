package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.application.ApplicationRepository;
import br.com.vortexlab.VortexLab.common.enums.ApplicationStatus;
import br.com.vortexlab.VortexLab.plan.dto.PlanBase;
import br.com.vortexlab.VortexLab.plan.dto.PlanRequest;
import br.com.vortexlab.VortexLab.plan.dto.PlanResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;

@ExtendWith(MockitoExtension.class)
public class PlanServiceTest {

  @Mock private PlanMapper planMapper;
  @Mock private PlanRepository planRepository;
  @Mock private ApplicationRepository applicationRepository;
  @InjectMocks private PlanService planService;

  private Plan plan;
  private PlanRequest planRequest;
  private PlanResponse planResponse;

  @BeforeEach
  void setUp() {
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);
    this.plan =
        Plan.builder()
            .name("Platinium")
            .description("something...")
            .type(PlanTypeRecorrence.MONTHLY)
            .price(new BigDecimal("10.00"))
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .build();

    this.planRequest =
        new PlanRequest(
            new PlanBase(
                "Platinium",
                "something...",
                PlanTypeRecorrence.MONTHLY,
                new BigDecimal("10.00"),
                "support 24h, 7 days on week; backup; 1000 emails"),
            1L);

    this.planResponse =
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
  }

  @Test
  @DisplayName("Given valid plan request, when register plan, then returns plan response")
  void givenValidPlanRequest_whenRegisterPlan_thenReturnPlanResponse() {
    // Given / Arrange: prepare initial data and mocks
    var planSaved =
        Plan.builder()
            .name("Platinium")
            .description("something...")
            .type(PlanTypeRecorrence.MONTHLY)
            .price(new BigDecimal("10.00"))
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .build();

    planSaved.setId(1L);

    Application application =
        Application.builder()
            .name("VortexLab")
            .description("something...")
            .url("https://vortexlab.com.br")
            .description("vortexLab is a software for developers...")
            .status(ApplicationStatus.ACTIVE)
            .build();

    application.setId(1L);

    // When / Act: call the method under test
    when(planMapper.toEntity(planRequest)).thenReturn(plan);
    when(planRepository.save(plan)).thenReturn(planSaved);
    when(applicationRepository.findById(planRequest.applicationId()))
        .thenReturn(Optional.of(application));
    when(planMapper.toResponse(planSaved)).thenReturn(planResponse);

    PlanResponse actualResponse = planService.registerPlan(planRequest);

    // Then / Assert: verify results
    assertThat(actualResponse, equalTo(planResponse));
    assertThat(actualResponse.id(), equalTo(planResponse.id()));
    assertThat(actualResponse.deleted(), is(false));

    // Verify mock interactions
    verify(planMapper, times(1)).toEntity(planRequest);
    verify(planRepository, times(1)).save(plan);
    verify(planMapper, times(1)).toResponse(planSaved);
    verify(applicationRepository, times(1)).findById(planRequest.applicationId());
  }

  @Test
  @DisplayName("Given valid plan ID, when findById is called, then returns plan")
  void givenValidPlanID_whenFindById_thenReturnPlan() {
    // Given / Arrange: prepare initial data and mocks
    this.plan.setId(1L);
    given(planRepository.findById(1L)).willReturn(Optional.of(plan));
    given(planMapper.toResponse(plan)).willReturn(planResponse);
    // When / Act: call the method under test

    PlanResponse actualResponse = planService.findById(1L);

    // Then / Assert: verify results or exceptions

    assertThat(actualResponse, equalTo(planResponse));
    assertThat(actualResponse.id(), equalTo(planResponse.id()));
    assertThat(actualResponse.deleted(), is(false));
    assertThat(actualResponse, notNullValue());
    assertThat(actualResponse.createdAt(), is(planResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given valid plan ID, when delete him, then returns nothing")
  void givenValidPlanID_whenDelete_thenDoesNotReturn() {
    // Given / Arrange: prepare initial data and mocks
    this.plan.setId(1L);
    given(planRepository.findById(1L)).willReturn(Optional.of(plan));
    given(planRepository.save(plan)).willReturn(plan);
    // When / Act: call the method under test

    planService.delete(plan.getId());

    // Then / Assert: verify results or exceptions

    // Optionally: verify mock interactions
    verify(planRepository, times(1)).save(plan);
    verify(planRepository, never()).delete(any());
  }

  @Test
  @DisplayName("Given valid plan data, when update his, then returns plan updated")
  void givenValidPlanData_whenUpdate_thenReturnUpdatedPlan() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);
    this.plan.setId(1L);

    this.planResponse =
        new PlanResponse(
            new PlanBase(
                "Platinium",
                "something...",
                PlanTypeRecorrence.MONTHLY,
                new BigDecimal("20.00"),
                "support 24h, 7 days on week; backup; 1000 emails"),
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    given(planRepository.findById(1L)).willReturn(Optional.of(plan));
    given(planMapper.toResponse(plan)).willReturn(planResponse);
    given(planRepository.save(plan)).willReturn(plan);
    // When / Act: call the method under test

    plan.setPrice(new BigDecimal("20.00"));
    PlanResponse planUpdatedResponse = planService.updatePlan(plan.getId(), planRequest);

    // Then / Assert: verify results or exceptions

    assertThat(planUpdatedResponse, equalTo(planResponse));
    assertThat(planUpdatedResponse.id(), equalTo(planResponse.id()));
    assertThat(planUpdatedResponse.deleted(), is(false));
    assertThat(planUpdatedResponse, notNullValue());
    assertThat(planUpdatedResponse.updatedAt(), is(planResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given plan list, when findAll plans is called, then returns list of plans")
  void givenPlanList_whenFindAll_thenReturnListOfPlans() {
    // Given / Arrange: prepare initial data and mocks
    Plan plan1 =
        Plan.builder()
            .name("Platinium")
            .description("something...")
            .type(PlanTypeRecorrence.MONTHLY)
            .price(new BigDecimal("10.00"))
            .resources("support 24h, 7 days on week; backup; 1000 emails")
            .build();
    plan1.setId(2L);

    PlanResponse planResponse1 =
        new PlanResponse(
            new PlanBase(
                "Platinium",
                "something...",
                PlanTypeRecorrence.MONTHLY,
                new BigDecimal("10.00"),
                "support 24h, 7 days on week; backup; 1000 emails"),
            2L,
            null,
            null,
            null,
            false);

    given(planRepository.findAll(ArgumentMatchers.any(Pageable.class)))
        .willReturn(new PageImpl<>(List.of(plan, plan1)));
    given(planMapper.toResponse(plan)).willReturn(planResponse);
    given(planMapper.toResponse(plan1)).willReturn(planResponse1);
    // When / Act: call the method under test

    Page<PlanResponse> allPlans = planService.getAllPlans(PageRequest.of(0, 10), null, null, null);

    // Then / Assert: verify results or exceptions

    assertThat(allPlans.getContent(), hasSize(2));
    assertThat(allPlans.getContent().get(0).id(), equalTo(1L));
    assertThat(allPlans.getContent().get(1).id(), equalTo(2L));

    // Optionally: verify mock interactions
  }
}
