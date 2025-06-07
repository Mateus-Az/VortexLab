package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.application.dto.ApplicationRequest;
import br.com.vortexlab.VortexLab.application.dto.ApplicationResponse;
import br.com.vortexlab.VortexLab.plan.Plan;
import br.com.vortexlab.VortexLab.plan.PlanMapper;
import br.com.vortexlab.VortexLab.user.User;
import br.com.vortexlab.VortexLab.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {PlanMapper.class, UserMapper.class})
public interface ApplicationMapper {


  Application toEntity(ApplicationRequest applicationDTO);

  @Mapping(target = "planIds", source = "plans")
  @Mapping(target = "userIds", source = "users")
  ApplicationResponse toResponse(Application application);

  default Set<Long> extractIdsPlan(Set<Plan> plans) {
    return plans == null
        ? Collections.emptySet()
        : plans.stream()
            .map(Plan::getId)
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  default Set<Long> extractIdsUser(Set<User> users) {
    return users == null
        ? Collections.emptySet()
        : users.stream()
            .map(User::getId)
            .sorted()
            .collect(Collectors.toCollection(LinkedHashSet::new));
  }

  void update(ApplicationRequest applicationDTO, @MappingTarget Application application);
}
