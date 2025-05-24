package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.application.ApplicationDTO;
import br.com.vortexlab.VortexLab.common.Views;
import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

  @JsonView({Views.Read.class})
  private Long id;

  @JsonView({Views.CreateUpdate.class})
  @NotBlank
  @Length(max = 50)
  private String name;

  @JsonView({Views.CreateUpdate.class})
  @Email
  @Schema(
          description = "Email address of the customer", example = "tutor@eazybytes.com"
  )
  private String email;

  @JsonView({Views.CreateUpdate.class})
  @NotBlank
  @Length(max = 50)
  private String password;

  @JsonView(Views.CreateUpdate.class)
  @NotBlank
  @Length(max = 50)
  private String username;

  @JsonView({Views.CreateUpdate.class})
  private String razaoSocial;

  @JsonView({Views.CreateUpdate.class})
  private String nomeFantasia;

  @JsonView({Views.CreateUpdate.class})
  private String cnpj;

  @JsonView({Views.CreateUpdate.class})
  private String cellPhone;

  @JsonView({Views.CreateUpdate.class})
  @NotBlank
  @Length(max = 11)
  private String cpf;

  @Enumerated(EnumType.STRING)
  @JsonProperty(access = JsonProperty.Access.READ_ONLY)
  private UserStatus status;
}
