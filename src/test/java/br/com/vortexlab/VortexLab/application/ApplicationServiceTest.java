package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.application.dto.ApplicationBase;
import br.com.vortexlab.VortexLab.application.dto.ApplicationRequest;
import br.com.vortexlab.VortexLab.application.dto.ApplicationResponse;
import br.com.vortexlab.VortexLab.common.enums.ApplicationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ApplicationServiceTest {

  @Mock private ApplicationRepository applicationRepository;

  @Mock private ApplicationMapper applicationMapper;

  @InjectMocks private ApplicationService applicationService;

  private ApplicationRequest applicationRequest;
  private Application application;
  private ApplicationResponse applicationResponse;

  @BeforeEach
  void setup() {
    applicationRequest =
        new ApplicationRequest(
            new ApplicationBase(
                "Platinium", "something...", "support 24h, 7 days on week; backup; 1000 emails"));

    application =
        Application.builder()
            .name("VortexLab")
            .description("something...")
            .url("https://vortexlab.com.br")
            .description("vortexLab is a software for developers...")
            .status(ApplicationStatus.ACTIVE)
            .build();

    application.setId(1L);
  }

  @Test
  @DisplayName("Given valid application request, when register, then returns application response")
  void givenValidApplicationRequest_whenRegister_thenReturnApplicationResponse() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);

    applicationResponse =
        new ApplicationResponse(
            new ApplicationBase(
                "VortexLab", "something...", "vortexLab is a software for developers..."),
            1L,
            fixedTime,
            fixedTime,
            null,
            null,
            false);

    // When / Act: call the method under test
    when(applicationMapper.toEntity(applicationRequest)).thenReturn(application);
    when(applicationRepository.save(application)).thenReturn(application);
    when(applicationMapper.toResponse(application)).thenReturn(applicationResponse);

    ApplicationResponse actualResponse = applicationService.registerApplication(applicationRequest);

    // Then / Assert: verify results
    assertThat(actualResponse, equalTo(applicationResponse));
    assertThat(actualResponse.id(), equalTo(application.getId()));
    assertThat(actualResponse.deleted(), is(false));

    // Verify mock interactions
    verify(applicationMapper, times(1)).toEntity(applicationRequest);
    verify(applicationRepository, times(1)).save(application);
    verify(applicationMapper, times(1)).toResponse(application);
  }

  @Test
  @DisplayName("Given valid application ID, when findById is called, then returns application")
  void givenValidApplicationID_whenFindById_thenReturnApplication() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);

    applicationResponse =
        new ApplicationResponse(
            new ApplicationBase(
                "VortexLab", "something...", "vortexLab is a software for developers..."),
            1L,
            fixedTime,
            fixedTime,
            null,
            null,
            false);

    given(applicationRepository.findById(1L)).willReturn(Optional.of(application));
    given(applicationMapper.toResponse(application)).willReturn(applicationResponse);
    // When / Act: call the method under test

    ApplicationResponse actualResponse = applicationService.findById(1L);

    // Then / Assert: verify results or exceptions

    assertThat(actualResponse, equalTo(applicationResponse));
    assertThat(actualResponse.id(), equalTo(applicationResponse.id()));
    assertThat(actualResponse.deleted(), is(false));
    assertThat(actualResponse, notNullValue());
    assertThat(actualResponse.createdAt(), is(applicationResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given valid application data, when update his, then returns application updated")
  void givenValidPlanData_whenUpdate_thenReturnUpdatedPlan() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);

    applicationResponse =
        new ApplicationResponse(
            new ApplicationBase(
                "VortexLab", "something...", "vortexLab is a software for developers..."),
            1L,
            fixedTime,
            fixedTime,
            null,
            null,
            false);

    given(applicationRepository.findById(1L)).willReturn(Optional.of(application));
    given(applicationMapper.toResponse(application)).willReturn(applicationResponse);
    given(applicationRepository.save(application)).willReturn(application);
    // When / Act: call the method under test

    application.setUrl("https://vortexlab.com.br");
    ApplicationResponse applicationUpdatedResponse =
        applicationService.updateApplication(application.getId(), applicationRequest);

    // Then / Assert: verify results or exceptions

    assertThat(applicationUpdatedResponse, equalTo(applicationResponse));
    assertThat(applicationUpdatedResponse.id(), equalTo(applicationResponse.id()));
    assertThat(applicationUpdatedResponse.deleted(), is(false));
    assertThat(applicationUpdatedResponse, notNullValue());
    assertThat(applicationUpdatedResponse.updatedAt(), is(applicationResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given valid application ID, when delete him, then returns nothing")
  void givenValidApplicationID_whenDelete_thenDoesNotReturn() {
    // Given / Arrange: prepare initial data and

    given(applicationRepository.findById(1L)).willReturn(Optional.of(application));
    given(applicationRepository.save(application)).willReturn(application);
    // When / Act: call the method under test

    applicationService.delete(application.getId());

    // Then / Assert: verify results or exceptions

    // Optionally: verify mock interactions
    verify(applicationRepository, times(1)).save(application);
    verify(applicationRepository, never()).delete(any());
  }

  @Test
  @DisplayName(
      "Given application list, when findAll applications is called, then returns list of aplications ")
  void givenApplicationList_whenFindAll_thenReturnListOfAplications() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);

    Application app1 =
        Application.builder()
            .name("VortexLab")
            .url("https://vortexlab.com.br")
            .description("vortexLab is a software for developers...")
            .status(ApplicationStatus.ACTIVE)
            .build();
    app1.setId(1L);

    Application app2 =
        Application.builder()
            .name("VortexLab2")
            .url("https://vortexlab2.com.br")
            .description("vortexLab is a software for developers...")
            .status(ApplicationStatus.ACTIVE)
            .build();
    app2.setId(2L);

    ApplicationResponse response1 =
        new ApplicationResponse(
            new ApplicationBase(
                "VortexLab",
                "https://vortexlab.com.br",
                "vortexLab is a software for developers..."),
            1L,
            fixedTime,
            fixedTime,
            null,
            null,
            false);

    ApplicationResponse response2 =
        new ApplicationResponse(
            new ApplicationBase(
                "VortexLab2",
                "https://vortexlab2.com.br",
                "vortexLab is a software for developers..."),
            2L, // ID deve corresponder ao app2
            fixedTime,
            fixedTime,
            null,
            null,
            false);

    given(applicationRepository.findAll(ArgumentMatchers.any(Pageable.class)))
        .willReturn(new PageImpl<>(List.of(app1, app2)));
    given(applicationMapper.toResponse(app1)).willReturn(response1);
    given(applicationMapper.toResponse(app2)).willReturn(response2);

    // When / Act: call the method under test
    Page<ApplicationResponse> applications = applicationService.getAll(PageRequest.of(0, 10));

    // Then / Assert: verify results
    assertThat(applications.getContent(), hasSize(2));
    assertThat(applications.getContent().get(0).id(), equalTo(1L));
    assertThat(applications.getContent().get(1).id(), equalTo(2L));
  }
}
