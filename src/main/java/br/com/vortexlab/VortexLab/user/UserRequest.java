package br.com.vortexlab.VortexLab.user;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest extends UserBase {
  UserBase user;

  @NotBlank
  @Length(max = 50)
  @Schema(description = "Password of the customer", example = "password")
  String password;

  @NotBlank
  @Schema(description = "Id of the application", example = "1")
  Long applicationId;
}
