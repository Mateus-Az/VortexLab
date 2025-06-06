package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.application.ApplicationResponse;
import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse extends UserBase {
  Long id;
  UserStatus status;
  Set<ApplicationResponse> applications;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  LocalDateTime deletedAt;
  Boolean deleted;
}
