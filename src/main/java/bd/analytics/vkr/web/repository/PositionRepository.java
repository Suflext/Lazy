package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<JobPosition, Long> {

    JobPosition findById(long id);
}
