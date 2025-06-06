package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.plan.PlanMapper;
import br.com.vortexlab.VortexLab.user.UserMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    uses = {PlanMapper.class, UserMapper.class})
public interface ApplicationMapper {

//  @Mapping(source = "", target = ".")
  Application toEntity(ApplicationRequest applicationDTO);

//  @Mapping(source = ".", target = "application")
//  @Mapping(source = "plans", target = "plans")
//  @Mapping(source = "users", target = "users")
  ApplicationResponse toResponse(Application application);

//  @Mapping(source = "application", target = ".")
  void update(ApplicationRequest applicationDTO, @MappingTarget Application application);
}
