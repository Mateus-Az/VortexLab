package br.com.vortexlab.VortexLab.applicationSchema;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/application/schema-config")
@RequiredArgsConstructor
public class ApplicationSchemaVersionController {
  private final ApplicationSchemaVersionService applicationSchemaVersionService;

  @PostMapping("/upload")
  public ResponseEntity<Void> uploadSchema(
      @RequestParam(value = "applicationId", required = true) Long applicationId,
      @RequestParam(value = "schemaVersion", required = true) String schemaVersion,
      @RequestBody String schemaDefinition) {
    this.applicationSchemaVersionService.uploadSchema(
        applicationId, schemaVersion, schemaDefinition);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @PostMapping("/apply-current-schema")
  public ResponseEntity<Void> applySchema(
      @RequestParam(value = "applicationId", required = true) Long applicationId,
      @RequestParam(value = "applicationSchemaId", required = true) Long applicationSchemaId) {
    this.applicationSchemaVersionService.applySchema(applicationId, applicationSchemaId);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping
  public List<ApplicationSchemaVersionResponse> list() {
    return this.applicationSchemaVersionService.findAll();
  }
}
