package analytics.repository;

import analytics.entity.WorkLogReport;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkLogReportRepository extends PagingAndSortingRepository<WorkLogReport, Long> {
    List<WorkLogReport> findAll();

    @Query(value = "SELECT * FROM  WORK_LOG_REPORT W WHERE W.START_DATE = ?1",
            nativeQuery = true)
    List<WorkLogReport> findAllByStartDate(LocalDate date);
}
