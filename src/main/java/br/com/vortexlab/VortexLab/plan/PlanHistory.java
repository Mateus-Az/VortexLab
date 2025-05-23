package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.biling.Billing;
import br.com.vortexlab.VortexLab.common.AbstractEntity;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "plan_history")
public class PlanHistory extends AbstractEntity {

  @OneToMany(mappedBy = "planHistory")
  private List<Billing> billings;

  @OneToOne
  @JoinColumn(name = "plan_active_id")
  private Plan planActive;

  @OneToOne
  @JoinColumn(name = "plan_previous_id")
  private Plan planPrevious;

  @OneToOne(mappedBy = "planHistory")
  private Application application;

}
