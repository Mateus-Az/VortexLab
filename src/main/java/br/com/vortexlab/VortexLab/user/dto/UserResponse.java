package br.com.vortexlab.VortexLab.user.dto;

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
  Set<Long> applicationIds;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  LocalDateTime deletedAt;
  Boolean deleted;
}
