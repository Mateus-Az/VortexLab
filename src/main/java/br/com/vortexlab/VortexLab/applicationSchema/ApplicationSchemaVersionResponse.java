package br.com.vortexlab.VortexLab.applicationSchema;

import br.com.vortexlab.VortexLab.application.Application;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationSchemaVersionResponse {
  Long id;
  String schemaVersion;
  Long applicationId;
  String schemaDefinition;
}
