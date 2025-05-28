package br.com.vortexlab.VortexLab.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
  boolean existsByEmail(String email);

  boolean existsByUsername(String username);

  boolean existsByCnpj(String cnpj);

  boolean existsByCpf(String cpf);

  boolean existsByCellPhone(String cellPhone);
}
