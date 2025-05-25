package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.common.enums.UserStatus;

public record UserResponse(UserBase user, UserStatus status) {}
