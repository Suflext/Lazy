package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {
}
