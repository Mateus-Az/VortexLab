package br.com.vortexlab.VortexLab.applicationSchema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationSchemaVersionRepository
    extends JpaRepository<ApplicationSchemaVersion, Long> {

  @Query(
      "SELECT asv FROM ApplicationSchemaVersion asv "
          + "WHERE asv.id = :id "
          + "AND asv.application.id = :applicationId")
  Optional<ApplicationSchemaVersion> findByApplicationSchemaIdAndApplicationId(
      @Param("id") Long id, @Param("applicationId") Long applicationId);

  Optional<ApplicationSchemaVersion> findBySchemaVersion(String schemaVersion);
}
