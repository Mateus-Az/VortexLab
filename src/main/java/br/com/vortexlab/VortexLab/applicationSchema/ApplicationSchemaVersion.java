package br.com.vortexlab.VortexLab.applicationSchema;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.common.AbstractEntity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSchemaVersion extends AbstractEntity {

  private String schemaVersion;

  @ManyToOne
  @JoinColumn(name = "application_id")
  @JsonManagedReference
  private Application application;

  @Lob private String schemaDefinition;
}
