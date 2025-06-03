package br.com.vortexlab.VortexLab.plan;

import java.time.LocalDateTime;

public record PlanResponse(
    PlanBase plan,
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    Boolean deleted) {}
