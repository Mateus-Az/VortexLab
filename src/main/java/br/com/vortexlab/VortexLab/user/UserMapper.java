package br.com.vortexlab.VortexLab.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  @Mapping(source = "user", target = ".")
  User toEntity(UserRequest userDTO);

  UserRequest toRequest(User user);

  @Mapping(source = ".", target = "user")
  UserResponse toResponse(User user);

  @Mapping(source = "user", target = ".")
  @Mapping(target = "password", ignore = true)
  void update(UserRequest userDTO, @MappingTarget User user);
}
