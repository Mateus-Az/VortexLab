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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

  @ManyToMany
  @JoinTable(
      name = "users_applications",
      joinColumns = @JoinColumn(name = "application_id"),
      inverseJoinColumns = @JoinColumn(name = "user_id"))
  @JsonManagedReference
  private Set<User> users;
}
