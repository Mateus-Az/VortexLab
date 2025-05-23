package br.com.vortexlab.VortexLab.biling;

import br.com.vortexlab.VortexLab.common.AbstractEntity;
import br.com.vortexlab.VortexLab.plan.PlanHistory;
import br.com.vortexlab.VortexLab.common.enums.PaymentMethod;
import br.com.vortexlab.VortexLab.common.enums.PaymentStatus;
import br.com.vortexlab.VortexLab.plan.Plan;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Billing extends AbstractEntity {

    @OneToOne
    @JoinColumn(name = "plan_id")
    private Plan plan;

    @Enumerated (EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated (EnumType.STRING)
    private PaymentMethod paymentMethod;

    @ManyToOne
    @JoinColumn(name = "plan_history_id")
    private PlanHistory planHistory;
}
