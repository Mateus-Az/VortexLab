package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;

import java.time.LocalDateTime;

public record UserResponse(
    UserBase user,
    UserStatus status,
    Long id,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt,
    Boolean deleted) {}
