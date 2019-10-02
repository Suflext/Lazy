package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.WorkLogAnalytics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkLogAnalyticsRepository extends JpaRepository<WorkLogAnalytics, Long> {
}
