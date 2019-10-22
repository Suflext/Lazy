package analytics.repository;

import analytics.entity.WorkLogReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface WorkLogReportRepository extends JpaRepository<WorkLogReport, Long> {

    ArrayList<WorkLogReport> findAll();
}
