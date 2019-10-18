package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    ArrayList<WorkLog> findAll();
}
