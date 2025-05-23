package br.com.vortexlab.VortexLab.tenant;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.application.Application;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tenants")
public class Tenant extends AbstractEntity {
  private String schema;

  @OneToOne(mappedBy = "tenant")
  @JsonBackReference
  private Application application;
}
