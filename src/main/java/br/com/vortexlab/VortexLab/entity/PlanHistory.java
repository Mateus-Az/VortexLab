package br.com.vortexlab.VortexLab.entity;

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
