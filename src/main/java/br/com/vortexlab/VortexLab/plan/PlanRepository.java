package br.com.vortexlab.VortexLab.plan;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PlanRepository extends JpaRepository<Plan, Long> {

  @Query(
      "SELECT p FROM Plan p WHERE "
          + "(:applicationId IS NULL OR p.application.id = :applicationId) "
          + "AND (:name IS NULL OR LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))) "
          + "AND (:description IS NULL OR LOWER(p.description) LIKE LOWER(CONCAT('%', :description, '%')))")
  Page<Plan> findAll(
      Pageable pageable,
      @Param("applicationId") Long applicationId,
      @Param("name") String name,
      @Param("description") String description);
}
