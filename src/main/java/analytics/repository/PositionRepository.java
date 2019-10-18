package analytics.repository;

import analytics.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<JobPosition, Long> {

    JobPosition findById(long id);
}
