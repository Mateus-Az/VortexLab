package br.com.vortexlab.VortexLab.plan;

import br.com.vortexlab.VortexLab.plan.dto.PlanRequest;
import br.com.vortexlab.VortexLab.plan.dto.PlanResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/plan")
@RequiredArgsConstructor
public class PlanController {

  private final PlanService planService;

  @PostMapping
  public ResponseEntity<PlanResponse> registerPlan(@RequestBody PlanRequest planDTO) {
    return ResponseEntity.status(HttpStatus.CREATED).body(this.planService.registerPlan(planDTO));
  }

  @GetMapping
  public Page<PlanResponse> getAllPlans(
      @RequestParam(value = "applicationId", required = false) Long applicationId,
      @RequestParam(value = "name", required = false, defaultValue = "") String name,
      @RequestParam(value = "description", required = false, defaultValue = "") String description,
      @PageableDefault(size = 10, sort = "name") Pageable pageable) {
    return this.planService.getAllPlans(pageable, applicationId, name, description);
  }

  @GetMapping("/{id}")
  public ResponseEntity<PlanResponse> getPlan(@PathVariable("id") Long id) {
    return ResponseEntity.status(HttpStatus.OK).body(this.planService.findById(id));
  }

  @PutMapping("/{id}")
  public ResponseEntity<PlanResponse> updatePlan(
      @PathVariable("id") Long id, @RequestBody PlanRequest planDTO) {
    return ResponseEntity.status(HttpStatus.OK).body(this.planService.updatePlan(id, planDTO));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
    this.planService.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
