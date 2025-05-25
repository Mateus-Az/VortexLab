package br.com.vortexlab.VortexLab.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationMapper {

  @Mapping(source = "application", target = ".")
  Application toEntity(ApplicationRequest applicationDTO);

  @Mapping(source = ".", target = "application")
  ApplicationResponse toResponse(Application application);

  @Mapping(source = "application", target = ".")
  void update(ApplicationRequest applicationDTO, @MappingTarget Application application);
}
