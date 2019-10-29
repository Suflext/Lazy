package analytics.repository;

import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    ArrayList<WorkLog> findAll();

    @Query(value = "SELECT SUM (COALESCE (W.END_TIME, LOCALTIME) - W.START_TIME)" +
            " FROM WORK_LOG W WHERE W.DAILY = ?1 AND W.EMPLOYEE = ?2 ",
            nativeQuery = true)
    String findAllByDailyAndEmployee(LocalDate localDate, long id);


    @Query(value = "SELECT SUM (COALESCE (W.END_TIME, LOCALTIME) - W.START_TIME)" +
            " FROM WORK_LOG W WHERE W.DAILY >= ?1 AND W.DAILY <= ?2 AND W.EMPLOYEE = ?3",
            nativeQuery = true)
    String findAllByDaily(LocalDate localDateStart, LocalDate localDateEnd, long id);

    @Query("SELECT w FROM WorkLog w WHERE w.endTime = null")
    ArrayList<WorkLog> findByEndTime();

    @Query(value = "SELECT * FROM WORK_LOG W WHERE W.END_TIME IS NULL AND W.EMPLOYEE = ?1",
            nativeQuery = true)
    WorkLog findByDailyAndEmployeeAndOrder(long id);

}