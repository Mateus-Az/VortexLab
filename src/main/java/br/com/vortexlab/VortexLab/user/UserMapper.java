package br.com.vortexlab.VortexLab.user;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.user.dto.UserRequest;
import br.com.vortexlab.VortexLab.user.dto.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

  User toEntity(UserRequest userDTO);

  UserRequest toRequest(User user);

  @Mapping(target = "applicationIds", source = "applications")
  UserResponse toResponse(User user);

  default Set<Long> extractIds(Set<Application> apps) {
    return apps == null
        ? Collections.emptySet()
        : apps.stream()
            .map(Application::getId)
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  @Mapping(target = "password", ignore = true)
  void update(UserRequest userDTO, @MappingTarget User user);
}
