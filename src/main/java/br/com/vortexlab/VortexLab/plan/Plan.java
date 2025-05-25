package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.application.Application;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Entity
@Data
@Table(name = "plans")
public class Plan extends AbstractEntity {

  private String name;
  private String description;

  @Enumerated(EnumType.STRING)
  private PlanTypeRecorrence type;
  private String resources;
  private BigDecimal price;

  @ManyToOne
  @JoinColumn(name = "application_id")
  private Application application;
}
