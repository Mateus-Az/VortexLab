package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.application.dto.ApplicationRequest;
import br.com.vortexlab.VortexLab.application.dto.ApplicationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ApplicationService {
  private final ApplicationMapper applicationMapper;
  private final ApplicationRepository applicationRepository;

  public ApplicationResponse registerApplication(ApplicationRequest applicationDTO) {
    Application application = applicationMapper.toEntity(applicationDTO);
    var applicationSaved = applicationRepository.save(application);
    return applicationMapper.toResponse(applicationSaved);
  }

  @Transactional
  public Page<ApplicationResponse> getAll(Pageable pageable) {
    var applications = this.applicationRepository.findAll(pageable);
    return applications.map(this.applicationMapper::toResponse);
  }

  @Transactional
  public ApplicationResponse findById(Long id) {
    var application =
        this.applicationRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
    return this.applicationMapper.toResponse(application);
  }

  public ApplicationResponse updateApplication(Long id, ApplicationRequest applicationDTO) {
    var application =
        this.applicationRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
    this.applicationMapper.update(applicationDTO, application);
    return this.applicationMapper.toResponse(this.applicationRepository.save(application));
  }

  public void delete(Long id) {
    var application =
        this.applicationRepository
            .findById(id)
            .orElseThrow(() -> new RuntimeException("Application not found"));
    application.setDeleted(true);
    application.setDeletedAt(LocalDateTime.now());
    this.applicationRepository.save(application);
  }
}
