package br.com.vortexlab.VortexLab.tenant;

import br.com.vortexlab.VortexLab.application.Application;
import br.com.vortexlab.VortexLab.application.ApplicationDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO {

    private String schema;


    private ApplicationDTO application;
}
