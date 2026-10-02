package requirements_api_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import requirements_api_java.model.Requirement;

public interface RequirementRepository extends JpaRepository<Requirement, Long> {
}