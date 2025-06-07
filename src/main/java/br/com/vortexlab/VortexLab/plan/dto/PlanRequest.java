package br.com.vortexlab.VortexLab.plan.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlanRequest extends PlanBase {
  @NotBlank
  @Schema(description = "Id of the application", example = "1")
  Long applicationId;
}
