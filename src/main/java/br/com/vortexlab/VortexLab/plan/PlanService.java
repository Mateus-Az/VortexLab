package br.com.vortexlab.VortexLab.plan;

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

  public PlanResponse registerPlan(PlanRequest planDTO) {
    Plan plan = planMapper.toEntity(planDTO);
    var planSaved = this.planRepository.save(plan);
    return planMapper.toResponse(planSaved);
  }

  public Page<PlanResponse> getAllPlans(Pageable pageable) {
    var plans = this.planRepository.findAll(pageable);
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
