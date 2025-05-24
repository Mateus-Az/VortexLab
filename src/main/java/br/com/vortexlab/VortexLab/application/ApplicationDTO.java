package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.common.enums.ApplicationStatus;
import br.com.vortexlab.VortexLab.plan.Plan;
import br.com.vortexlab.VortexLab.plan.PlanHistory;
import br.com.vortexlab.VortexLab.tenant.Tenant;
import br.com.vortexlab.VortexLab.tenant.TenantDTO;
import br.com.vortexlab.VortexLab.user.User;
import br.com.vortexlab.VortexLab.user.UserDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ApplicationDTO {

  private String name;
  private String url;
  private String description;

  @Enumerated(EnumType.STRING)
  private ApplicationStatus status;

  private TenantDTO tenant;

//  private List<PlanDTO> plans;

  private PlanHistory planHistory;

  @JsonBackReference
  private UserDTO user;
}
