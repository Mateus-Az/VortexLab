package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.user.dto.UserRequest;
import br.com.vortexlab.VortexLab.user.dto.UserResponse;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
  private final UserService userService;

  @PostMapping
  public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.userService.registerUser(userDTO));
  }

  @GetMapping("/verify-email")
  public ResponseEntity ve(@RequestParam(name = "emailToken", required = true) String emailToken) {
    this.userService.verifyUserEmail(emailToken);
    return ResponseEntity.status(HttpStatus.OK).build();
  }

  @PostMapping("/request-email-verification/{id}")
  public ResponseEntity<Void> requestEmailVerification(@PathVariable("id") Long userId) {
    this.userService.requestEmailVerification(userId);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @PutMapping("/{id}")
  public ResponseEntity<UserResponse> updateUser(
      @PathVariable("id") Long id, @RequestBody UserRequest userDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.updateUser(id, userDTO));
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.userService.getUser(id));
  }

  @GetMapping
  public Page<UserResponse> getAllUsers(
      @PageableDefault(size = 10, sort = "name") Pageable pageable) {
    return this.userService.getAllUsers(pageable);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    this.userService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/exists")
  public boolean exists(
      @RequestParam(required = false) String email,
      @RequestParam(required = false) String username,
      @RequestParam(required = false) String cnpj,
      @RequestParam(required = false) String cpf,
      @RequestParam(required = false) String cellPhone) {
    return this.userService.exists(email, username, cnpj, cpf, cellPhone);
  }
}
