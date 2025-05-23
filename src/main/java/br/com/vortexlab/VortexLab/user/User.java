package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User extends AbstractEntity {
  private String name;
  private String email;
  private String password;
  private String username;
  private String razaoSocial;
  private String nomeFantasia;
  private String cnpj;
  private String cellPhone;
  private String cpf;

  @Enumerated(EnumType.STRING)
  private UserStatus status;

  @OneToMany
  @JoinColumn(name = "user_id")
  private List<Application> applications;
}
