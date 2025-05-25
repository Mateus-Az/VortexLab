package br.com.vortexlab.VortexLab.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

public record UserRequest(
    UserBase user,
    @NotBlank
        @Length(max = 50)
        @Schema(description = "Password of the customer", example = "password")
        String password) {}
