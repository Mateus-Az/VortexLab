package br.com.vortexlab.VortexLab.application;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationBase {
  @NotBlank
  @Length(max = 50)
  @Schema(description = "Name of the application", example = "VortexLab")
  String name;

  @NotBlank
  @Schema(description = "Url of the application", example = "https://vortexlab.com.br")
  String url;

  @Schema(description = "Description of the application", example = "VortexLab")
  String description;
}
