package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User extends AbstractEntity {
  @Column(nullable = false, length = 100)
  private String name;

  @Column(nullable = false, length = 100, unique = true)
  private String email;

  @Column(nullable = false)
  private String password;

  @Column(nullable = false, length = 15, unique = true)
  private String username;

  @Column(nullable = true, unique = true)
  private String razaoSocial;

  private String nomeFantasia;

  @Column(nullable = true, unique = true)
  private String cnpj;

  @Column(nullable = true, unique = true)
  private String cellPhone;

  @Column(unique = true)
  private String cpf;

  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @ManyToMany(mappedBy = "users")
  private List<Application> applications;
}
