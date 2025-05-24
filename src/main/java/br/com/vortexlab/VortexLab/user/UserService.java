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

  public UserDTO registerUser(UserDTO userDTO) {
    var user = this.userMapper.toEntity(userDTO);
    user.setStatus(UserStatus.REGISTERED);
    var savedUser = this.userRepository.save(user);
    return this.userMapper.toDTO(savedUser);
  }

  public UserDTO updateUser(Long id, UserDTO userDTO) {
    var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    this.userMapper.update(userDTO, user);
    return this.userMapper.toDTO(this.userRepository.save(user));
  }

  public UserDTO getUser(Long id) {
    var user = userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    return this.userMapper.toDTO(user);
  }

  public Page<UserDTO> getAllUsers(Pageable pageable) {
    var users = this.userRepository.findAll(pageable);
    return users.map(this.userMapper::toDTO);
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
