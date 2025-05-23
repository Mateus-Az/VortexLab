package br.com.vortexlab.VortexLab.entity;

import br.com.vortexlab.VortexLab.enums.ApplicationStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "applications")
public class Application extends AbstractEntity {
  private String name;
  private String url;
  private String description;

  @Enumerated(EnumType.STRING)
  private ApplicationStatus status;

  @OneToOne
  @JoinColumn(name = "tenant_id")
  @JsonManagedReference
  private Tenant tenant;

  @OneToMany(mappedBy = "application")
  @JsonManagedReference
  private List<Plan> plans;

  @OneToOne
  @JoinColumn(name = "billing_history_id")
  @JsonManagedReference
  private PlanHistory planHistory;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
}
