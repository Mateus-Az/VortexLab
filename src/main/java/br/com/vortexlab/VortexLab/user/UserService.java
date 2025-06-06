package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.application.ApplicationRepository;
import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;
  private final ApplicationRepository applicationRepository;

  @Transactional
  public UserResponse registerUser(UserRequest userDTO) {

    Application application =
        this.applicationRepository
            .findById(userDTO.getApplicationId())
            .orElseThrow(() -> new RuntimeException("Application not found"));

    boolean exists =
        this.exists(
            userDTO.getEmail(),
            userDTO.getUsername(),
            userDTO.getCnpj(),
            userDTO.getCpf(),
            userDTO.getCellPhone());
    if (exists) {
      throw new RuntimeException("User already exists");
    }

    application
        .getUsers()
        .forEach(
            user -> {
              if (isUserDuplicated(user, userDTO)) {
                throw new RuntimeException("User already registered in this application");
              }
            });

    var user = this.userMapper.toEntity(userDTO);
    user.setStatus(UserStatus.REGISTERED);
    user.getApplications().add(application);
    var savedUser = this.userRepository.save(user);
    return this.userMapper.toResponse(savedUser);
  }

  public UserResponse updateUser(Long id, UserRequest userDTO) {
    var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    this.userMapper.update(userDTO, user);
    return this.userMapper.toResponse(this.userRepository.save(user));
  }

  public UserResponse getUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    return this.userMapper.toResponse(user);
  }

  public Page<UserResponse> getAllUsers(Pageable pageable) {
    var users = this.userRepository.findAll(pageable);
    return users.map(this.userMapper::toResponse);
  }

  public void delete(Long id) {
    var user = this.userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    user.setDeleted(true);
    this.userRepository.save(user);
  }

  public boolean exists(String email, String username, String cnpj, String cpf, String cellPhone) {
    return this.userRepository.existsByEmail(email)
        || this.userRepository.existsByUsername(username)
        || this.userRepository.existsByCnpj(cnpj)
        || this.userRepository.existsByCpf(cpf)
        || this.userRepository.existsByCellPhone(cellPhone);
  }

  private boolean isUserDuplicated(User existingUser, UserBase newUser) {
    return equalsIgnoreCase(existingUser.getEmail(), newUser.getEmail())
        || equalsIgnoreCase(existingUser.getUsername(), newUser.getUsername())
        || equalsIgnoreCase(existingUser.getCnpj(), newUser.getCnpj())
        || equalsIgnoreCase(existingUser.getCpf(), newUser.getCpf())
        || equalsIgnoreCase(existingUser.getCellPhone(), newUser.getCellPhone());
  }

  private boolean equalsIgnoreCase(String str1, String str2) {
    if (str1 == null || str2 == null) return false;
    return str1.equalsIgnoreCase(str2);
  }
}
