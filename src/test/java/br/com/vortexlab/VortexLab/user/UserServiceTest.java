package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import br.com.vortexlab.VortexLab.plan.PlanBase;
import br.com.vortexlab.VortexLab.plan.PlanResponse;
import br.com.vortexlab.VortexLab.plan.PlanTypeRecorrence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserMapper userMapper;

  @Mock private UserRepository userRepository;

  @InjectMocks private UserService userService;

  private UserRequest userRequest;
  private User user;
  private UserResponse userResponse;

  @BeforeEach
  void setUp() {

    userRequest =
        new UserRequest(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            "killJedi");

    user =
        User.builder()
            .name("Darth Maul")
            .email("darth@maul.com")
            .password("killJedi")
            .username("Darth Maul")
            .razaoSocial("Darth Maul")
            .nomeFantasia("Darth Maul")
            .cnpj("73.345.971/0001-47")
            .cellPhone("12345678934")
            .cpf("216.631.360-41")
            .build();
    user.setId(1L);
  }

  @Test
  @DisplayName("Given valid user request, when register, then returns user response")
  void givenValidUserRequest_whenRegister_thenReturnUserResponse() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);
    userResponse =
        new UserResponse(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            UserStatus.REGISTERED,
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    // When / Act: call the method under test
    when(userMapper.toEntity(userRequest)).thenReturn(user);
    when(userRepository.save(user)).thenReturn(user);
    when(userMapper.toResponse(user)).thenReturn(userResponse);

    UserResponse actualResponse = userService.registerUser(userRequest);

    // Then / Assert: verify results
    assertThat(actualResponse, equalTo(userResponse));
    assertThat(actualResponse.id(), equalTo(userResponse.id()));
    assertThat(actualResponse.deleted(), is(false));

    // Verify mock interactions
    verify(userMapper, times(1)).toEntity(userRequest);
    verify(userRepository, times(1)).save(user);
    verify(userMapper, times(1)).toResponse(user);
  }

  @Test
  @DisplayName("Given valid user ID, when delete him, then returns nothing")
  void givenValidUserID_whenDelete_thenDoesNotReturn() {
    // Given / Arrange: prepare initial data and mocks
    given(userRepository.findById(1L)).willReturn(Optional.of(user));
    given(userRepository.save(user)).willReturn(user);
    // When / Act: call the method under test

    userService.delete(user.getId());

    // Then / Assert: verify results or exceptions

    // Optionally: verify mock interactions
    verify(userRepository, times(1)).save(user);
    verify(userRepository, never()).delete(any());
  }

  @Test
  @DisplayName("Given user list, when findAll user is called, then returns list of user")
  void givenUserList_whenFindAll_thenReturnListOfUsers() {
    // Given / Arrange: prepare initial data and mocks

    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);
    userResponse =
        new UserResponse(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            UserStatus.REGISTERED,
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    User user1 =
        User.builder()
            .name("Darth Maul")
            .email("darth@maul.com")
            .password("killJedi")
            .username("Darth Maul")
            .razaoSocial("Darth Maul")
            .nomeFantasia("Darth Maul")
            .cnpj("73.345.971/0001-47")
            .cellPhone("12345678934")
            .cpf("216.631.360-41")
            .build();
    user1.setId(2L);

    UserResponse UserResponse1 =
        new UserResponse(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            UserStatus.REGISTERED,
            2L,
            null,
            null,
            null,
            false);

    given(userRepository.findAll(ArgumentMatchers.any(Pageable.class)))
        .willReturn(new PageImpl<>(List.of(user, user1)));
    given(userMapper.toResponse(user)).willReturn(userResponse);
    given(userMapper.toResponse(user1)).willReturn(UserResponse1);
    // When / Act: call the method under test

    Page<UserResponse> users = userService.getAllUsers(PageRequest.of(0, 10));

    // Then / Assert: verify results or exceptions

    assertThat(users.getContent(), hasSize(2));
    assertThat(users.getContent().get(0).id(), equalTo(1L));
    assertThat(users.getContent().get(1).id(), equalTo(2L));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given valid user data, when update his, then returns user updated")
  void givenValidPlanData_whenUpdate_thenReturnUpdatedPlan() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);

    userResponse =
        new UserResponse(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul LTDA",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            UserStatus.REGISTERED,
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    given(userRepository.findById(1L)).willReturn(Optional.of(user));
    given(userMapper.toResponse(user)).willReturn(userResponse);
    given(userRepository.save(user)).willReturn(user);
    // When / Act: call the method under test

    user.setNomeFantasia("Darth Maul LTDA");
    UserResponse userUpdatedResponse = userService.updateUser(user.getId(), userRequest);

    // Then / Assert: verify results or exceptions

    assertThat(userUpdatedResponse, equalTo(userResponse));
    assertThat(userUpdatedResponse.id(), equalTo(userResponse.id()));
    assertThat(userUpdatedResponse.deleted(), is(false));
    assertThat(userUpdatedResponse, notNullValue());
    assertThat(userUpdatedResponse.updatedAt(), is(userResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given valid user ID, when findById is called, then returns user")
  void givenValidPlanID_whenFindById_thenReturnPlan() {
    // Given / Arrange: prepare initial data and mocks
    LocalDateTime fixedTime = LocalDateTime.of(2025, 1, 1, 10, 0);
    userResponse =
        new UserResponse(
            new UserBase(
                "Darth Maul",
                "darth@maul.com",
                "killJedi",
                "Darth Maul",
                "Darth Maul LTDA",
                "73.345.971/0001-47",
                "12345678934",
                "216.631.360-41"),
            UserStatus.REGISTERED,
            1L,
            fixedTime,
            fixedTime,
            null,
            false);

    given(userRepository.findById(1L)).willReturn(Optional.of(user));
    given(userMapper.toResponse(user)).willReturn(userResponse);
    // When / Act: call the method under test

    UserResponse actualResponse = userService.getUser(1L);

    // Then / Assert: verify results or exceptions

    assertThat(actualResponse, equalTo(userResponse));
    assertThat(actualResponse.id(), equalTo(userResponse.id()));
    assertThat(actualResponse.deleted(), is(false));
    assertThat(actualResponse, notNullValue());
    assertThat(actualResponse.createdAt(), is(userResponse.createdAt()));

    // Optionally: verify mock interactions
  }

  @Test
  @DisplayName("Given existing info, when check if user exists, then returns the true")
  @MockitoSettings(strictness = Strictness.LENIENT)
  void givenPartialUser_whenCheckIfUserExists_thenReturnTrue() {
    // Given / Arrange: prepare initial data and mocks

    given(userRepository.existsByCpf("216.631.360-41")).willReturn(true);
    given(userRepository.existsByEmail("darth@maul.com")).willReturn(true);
    given(userRepository.existsByUsername("killJedi")).willReturn(true);
    given(userRepository.existsByCnpj("73.345.971/0001-47")).willReturn(true);
    given(userRepository.existsByCellPhone("12345678934")).willReturn(true);

    // When / Act: call the method under test

    Boolean userExistsByEmail = userService.exists("darth@maul.com", null, null, null, null);
    Boolean userExistsByCpf = userService.exists(null, null, null, "216.631.360-41", null);
    Boolean userExistsByCnpj = userService.exists(null, null, "73.345.971/0001-47", null, null);
    Boolean userExistsByUsername = userService.exists(null, "killJedi", null, null, null);
    Boolean userExistsByCellPhone = userService.exists(null, null, null, null, "12345678934");
    // Then / Assert: verify results or exceptions

    assertThat(userExistsByEmail, is(true));
    assertThat(userExistsByCpf, is(true));
    assertThat(userExistsByCnpj, is(true));
    assertThat(userExistsByUsername, is(true));
    assertThat(userExistsByCellPhone, is(true));

    // Optionally: verify mock interactions
    verify(userRepository).existsByEmail("darth@maul.com");
    verify(userRepository).existsByCpf("216.631.360-41");
    verify(userRepository).existsByCnpj("73.345.971/0001-47");
    verify(userRepository).existsByUsername("killJedi");
    verify(userRepository).existsByCellPhone("12345678934");
  }
}
