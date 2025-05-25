package br.com.vortexlab.VortexLab.application;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.plan.PlanHistory;
import br.com.vortexlab.VortexLab.common.enums.ApplicationStatus;
import br.com.vortexlab.VortexLab.plan.Plan;
import br.com.vortexlab.VortexLab.tenant.Tenant;
import br.com.vortexlab.VortexLab.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
  @JsonBackReference
  private User user;

  @ManyToMany
  @JoinTable(
      name = "users_applications",
      joinColumns = @JoinColumn(name = "application_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  @JsonManagedReference
  private List<User> users;
}
