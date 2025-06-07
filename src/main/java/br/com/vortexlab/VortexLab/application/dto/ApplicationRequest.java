package br.com.vortexlab.VortexLab.application.dto;

import br.com.vortexlab.VortexLab.common.enums.ApplicationStatus;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationRequest extends ApplicationBase {
  ApplicationStatus status;
}
