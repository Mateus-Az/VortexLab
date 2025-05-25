package br.com.vortexlab.VortexLab.plan;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

public record PlanBase(
    @NotBlank @Length(max = 50) @Schema(description = "Name of the plan", example = "Platinium II")
        String name,
    String description,
    @NotBlank
        @Schema(
            description = "Type of recorrence of the plan",
            example = "LIFETIME",
            allowableValues = "LIFETIME, MONTHLY, QUARTERLY, SEMIANNUAL, ANNUAL, CREDITS")
        PlanTypeRecorrence type,
    @Positive @Schema(description = "Price of the plan", example = "99.99") BigDecimal price,
    @Schema(
            description = "Resources of the plan",
            example = "support 24h, 7 days on week; backup; 1000 emails")
        String resources) {}
