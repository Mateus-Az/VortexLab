package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  private Long id;

  @NotBlank
  @Length(max = 50)
  private String name;

  @Email
  @Schema(description = "Email address of the customer", example = "tutor@eazybytes.com")
  private String email;

  @NotBlank
  @Length(max = 50)
  private String password;

  @NotBlank
  @Length(max = 50)
  private String username;

  private String razaoSocial;

  private String nomeFantasia;

  private String cnpj;

  private String cellPhone;

  @NotBlank
  @Length(max = 11)
  private String cpf;

  @Enumerated(EnumType.STRING)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UserStatus status;
}
