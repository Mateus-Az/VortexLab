package br.com.vortexlab.VortexLab.application;

import java.time.LocalDateTime;

public record ApplicationResponse(
    ApplicationBase application,
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    Boolean deleted) {}
