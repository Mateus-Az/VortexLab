package br.com.vortexlab.VortexLab.application;

public record ApplicationResponse(
    ApplicationBase application,
    String id,
    String createdAt,
    String updatedAt,
    String deletedAt,
    Boolean deleted) {}
