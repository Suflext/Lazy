package analytics.repository;

import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    ArrayList<WorkLog> findAll();

    ArrayList<WorkLog> findAllByDaily(LocalDate date);

}