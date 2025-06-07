package br.com.vortexlab.VortexLab.common;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EmailVerificationData {
  private Long userId;
  private String email;
  private String username;
}
