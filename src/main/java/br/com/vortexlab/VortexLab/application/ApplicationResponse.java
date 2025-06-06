package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.plan.PlanResponse;
import br.com.vortexlab.VortexLab.user.UserResponse;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationResponse extends ApplicationBase {
  Long id;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  LocalDateTime deletedAt;
  List<PlanResponse> plans;
  Set<UserResponse> users;
  Boolean deleted;
}
