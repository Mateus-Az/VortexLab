package br.com.vortexlab.VortexLab.entity;

import br.com.vortexlab.VortexLab.enums.PaymentMethod;
import br.com.vortexlab.VortexLab.enums.PaymentStatus;
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
