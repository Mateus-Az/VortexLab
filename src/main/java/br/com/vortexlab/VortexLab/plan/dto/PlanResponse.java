package br.com.vortexlab.VortexLab.plan.dto;

import java.time.LocalDateTime;

public class PlanResponse extends PlanBase {
  Long id;
  LocalDateTime createdAt;
  LocalDateTime updatedAt;
  LocalDateTime deletedAt;
  Boolean deleted;
}
