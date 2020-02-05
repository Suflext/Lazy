package analytics.repository;

import analytics.entity.WorkLogReport;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface WorkLogReportRepository extends PagingAndSortingRepository<WorkLogReport, Long> {
    List<WorkLogReport> findAll();
}
