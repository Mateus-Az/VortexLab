package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserResponse registerUser(UserRequest userDTO) {
    var user = this.userMapper.toEntity(userDTO);
    user.setStatus(UserStatus.REGISTERED);
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
}
