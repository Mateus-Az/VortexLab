package br.com.vortexlab.VortexLab.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/*
 * Don't use this record in operations, is just for composition of other records
 */
public record UserBase(
    @NotBlank
        @Length(max = 50)
        @Schema(description = "Full name of the customer", example = "John Doe")
        String name,
    @NotBlank
        @Email
        @Schema(description = "Email address of the customer", example = "user@email.com")
        String email,
    @NotBlank @Length(max = 50) @Schema(example = "imperator") String username,
    @Schema(description = "Razao Social of the customer", example = "Google INC")
        String razaoSocial,
    @Schema(description = "Nome Fantasia of the customer", example = "Google") String nomeFantasia,
    @Schema(description = "CNPJ of the customer", example = "73.345.971/0001-47") String cnpj,
    String cellPhone,
    @NotBlank @Length(max = 14) @Schema(example = "216.631.360-41") String cpf) {}
