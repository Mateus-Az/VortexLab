package br.com.vortexlab.VortexLab.applicationSchema;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.application.ApplicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ApplicationSchemaVersionService {
  private final ApplicationSchemaVersionRepository applicationSchemaRepository;
  private final ApplicationRepository applicationRepository;
  private final ApplicationSchemaVersionMapper applicationSchemaVersionMapper;

  @Transactional
  public void uploadSchema(Long applicationId, String schemaVersion, String schemaDefinition) {

    Application application =
        this.applicationRepository
            .findById(applicationId)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    this.applicationSchemaRepository
        .findBySchemaVersion(schemaVersion)
        .ifPresent(
            schema -> {
              throw new RuntimeException("Schema version already exists");
            });

    ApplicationSchemaVersion applicationSchemaVersion =
        ApplicationSchemaVersion.builder()
            .application(application)
            .schemaVersion(schemaVersion)
            .schemaDefinition(schemaDefinition)
            .build();

    application.addApplicationSchemaVersion(
        this.applicationSchemaRepository.save(applicationSchemaVersion));
    Application applicationSaved = this.applicationRepository.save(application);
  }

  @Transactional
  public void applySchema(Long applicationId, Long applicationSchemaId) {
    Application application =
        this.applicationRepository
            .findById(applicationId)
            .orElseThrow(() -> new RuntimeException("Application not found"));

    this.applicationSchemaRepository
        .findByApplicationSchemaIdAndApplicationId(applicationSchemaId, applicationId)
        .orElseThrow(() -> new RuntimeException("Application Schema not found"));

    application.setCurrentSchemaVersionId(applicationSchemaId);
    this.applicationRepository.save(application);
  }

  @Transactional
  public List<ApplicationSchemaVersionResponse> findAll() {
    List<ApplicationSchemaVersion> applicationSchemaVersions =
        this.applicationSchemaRepository.findAll();

    return applicationSchemaVersions.stream()
        .map(this.applicationSchemaVersionMapper::toResponse)
        .collect(Collectors.toList());
  }
}
