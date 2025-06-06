package br.com.vortexlab.VortexLab.application;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/application")
@RequiredArgsConstructor
public class ApplicationController {
  private final ApplicationService applicationService;

  @PostMapping
  public ResponseEntity<ApplicationResponse> registerApplication(
      @RequestBody ApplicationRequest applicationDTO) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(this.applicationService.registerApplication(applicationDTO));
  }

  @GetMapping
  public ResponseEntity<Page<ApplicationResponse>> getAllApplications(
      @PageableDefault(page = 0, size = 10) Pageable pageable) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.applicationService.getAll(pageable));
  }

  @GetMapping("/{id}")
  public ResponseEntity<ApplicationResponse> getApplication(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.applicationService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ApplicationResponse> updateApplication(
      @PathVariable("id") Long id, @RequestBody ApplicationRequest applicationDTO) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(this.applicationService.updateApplication(id, applicationDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteApplication(@PathVariable("id") Long id) {
    this.applicationService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
