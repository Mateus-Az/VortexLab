package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.application.ApplicationRepository;
import br.com.vortexlab.VortexLab.plan.dto.PlanRequest;
import br.com.vortexlab.VortexLab.plan.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PlanService {
  private final PlanRepository planRepository;
  private final PlanMapper planMapper;
  private final ApplicationRepository applicationRepository;

  public PlanResponse registerPlan(PlanRequest planDTO) {
    var application =
        this.applicationRepository
            .findById(planDTO.getApplicationId())
            .orElseThrow(() -> new RuntimeException("Application not found"));
    Plan plan = planMapper.toEntity(planDTO);
    plan.setApplication(application);
    var planSaved = this.planRepository.save(plan);
    return planMapper.toResponse(planSaved);
  }

  public Page<PlanResponse> getAllPlans(
      Pageable pageable, Long applicationId, String name, String description) {
    var plans = this.planRepository.findAll(pageable, applicationId, name, description);
    return plans.map(this.planMapper::toResponse);
  }

  public PlanResponse findById(Long id) {
    var plan = this.planRepository.findById(id).orElseThrow();
    return this.planMapper.toResponse(plan);
  }

  public PlanResponse updatePlan(Long id, PlanRequest planDTO) {
    var plan = this.planRepository.findById(id).orElseThrow();
    this.planMapper.update(planDTO, plan);
    return this.planMapper.toResponse(this.planRepository.save(plan));
  }

  public void delete(Long id) {
    var plan = this.planRepository.findById(id).orElseThrow();
    plan.setDeleted(true);
    plan.setDeletedAt(LocalDateTime.now());
    this.planRepository.save(plan);
  }
}
