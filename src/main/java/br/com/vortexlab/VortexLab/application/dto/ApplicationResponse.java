package br.com.vortexlab.VortexLab.application.dto;

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
  Set<Long> planIds;
  Set<Long> userIds;
  Boolean deleted;
}
