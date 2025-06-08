package br.com.vortexlab.VortexLab.applicationSchema;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ApplicationSchemaVersionMapper {

  @Mapping(target = "applicationId", source = "application.id")
  ApplicationSchemaVersionResponse toResponse(ApplicationSchemaVersion applicationSchemaVersion);
}
