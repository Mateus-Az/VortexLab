package br.com.vortexlab.VortexLab.plan;

public record PlanResponse(
    PlanBase plan, String createdAt, String updatedAt, Boolean deleted, String deletedAt) {}
