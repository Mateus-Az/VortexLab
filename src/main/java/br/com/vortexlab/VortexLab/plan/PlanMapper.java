package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.plan.dto.PlanRequest;
import br.com.vortexlab.VortexLab.plan.dto.PlanResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlanMapper {

//  @Mapping(source = "plan", target = ".")
  Plan toEntity(PlanRequest planDTO);

//  @Mapping(source = ".", target = "plan")
  PlanResponse toResponse(Plan plan);

//  @Mapping(source = "plan", target = ".")
  void update(PlanRequest planDTO, @MappingTarget Plan plan);
}
