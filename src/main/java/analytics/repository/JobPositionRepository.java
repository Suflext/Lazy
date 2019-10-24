package analytics.repository;

import analytics.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {

    JobPosition findById(long id);
}
