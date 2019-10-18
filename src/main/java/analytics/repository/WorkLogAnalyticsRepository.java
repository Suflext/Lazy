package analytics.repository;

import analytics.entity.WorkLogReport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogAnalyticsRepository extends JpaRepository<WorkLogReport, Long> {
}
