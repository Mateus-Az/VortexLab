package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
public class UserRepositoryTest {

  private User user;

  @Autowired private UserRepository repository;

  @BeforeEach
  void setUp() {
    user =
        User.builder()
            .name("Darth maul")
            .email("darth@maul.com")
            .password("killJedi")
            .username("Darth maul")
            .razaoSocial("Darth maul")
            .nomeFantasia("Darth maul")
            .cnpj("11.111.111/1111-11")
            .cellPhone("12345678934")
            .cpf("12345678934")
            .build();
  }

  @Test
  @DisplayName(
      "Given user with valid email, when verify if exist user with this email, then returns the true")
  void givenValidUser_whenVerifyIfExistUserByEmail_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks
    this.repository.save(user);

    // When / Act: call the method under test
    Boolean existUser = repository.existsByEmail(user.getEmail());

    // Then / Assert: verify results or exceptions

    assertThat(existUser, is(true));
  }

  @Test
  @DisplayName(
      "Given user with valid username, when verify if exist user with this username, then returns the true")
  void givenValidUser_whenVerifyIfExistUserByUsername_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks
    this.repository.save(user);

    // When / Act: call the method under test
    Boolean existUser = repository.existsByUsername(user.getUsername());

    // Then / Assert: verify results or exceptions

    assertThat(existUser, is(true));
  }

  @Test
  @DisplayName(
      "Given user with valid cnpj, when verify if exist user with this cnpj, then returns the true")
  void givenValidUser_whenVerifyIfExistUserByCnpj_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks
    this.repository.save(user);

    // When / Act: call the method under test
    Boolean existUser = repository.existsByCnpj(user.getCnpj());

    // Then / Assert: verify results or exceptions

    assertThat(existUser, is(true));
  }

  @Test
  @DisplayName(
      "Given user with valid cpf, when verify if exist user with this cpf, then returns the true")
  void givenValidUser_whenVerifyIfExistUserByCpf_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks
    this.repository.save(user);

    // When / Act: call the method under test
    Boolean existUser = repository.existsByCpf(user.getCpf());

    // Then / Assert: verify results or exceptions

    assertThat(existUser, is(true));
  }

  @Test
  @DisplayName(
      "Given user with valid cellPhone, when verify if exist user with this cellPhone, then returns the true")
  void givenValidUser_whenVerifyIfExistUserByCellPhone_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks
    this.repository.save(user);

    // When / Act: call the method under test
    Boolean existUser = repository.existsByCellPhone(user.getCellPhone());

    // Then / Assert: verify results or exceptions

    assertThat(existUser, is(true));
  }
}
