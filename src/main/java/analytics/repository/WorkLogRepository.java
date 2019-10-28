package analytics.repository;

import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    ArrayList<WorkLog> findAll();

    @Query("SELECT w FROM WorkLog w WHERE w.daily = ?1 AND w.employee.id = ?2")
    ArrayList<WorkLog> findAllByDailyAndEmployee(LocalDate localDate, long id);


    @Query("SELECT w FROM WorkLog w WHERE w.daily >= ?1 AND w.daily <= ?2 AND w.employee.id = ?3")
    ArrayList<WorkLog> findAllByDaily(LocalDate localDateStart, LocalDate localDateEnd, long id);

    @Query("SELECT w FROM WorkLog w WHERE w.endTime = null")
    ArrayList<WorkLog> findByEndTime();

    @Query("SELECT w FROM WorkLog w WHERE w.employee.id = ?1")
    ArrayList<WorkLog> findAllByEmployeeId(long id);
}