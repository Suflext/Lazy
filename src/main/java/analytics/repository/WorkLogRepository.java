package analytics.repository;

import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.time.LocalDate;
import java.util.List;

public interface WorkLogRepository extends PagingAndSortingRepository<WorkLog, Long> {

    List<WorkLog> findAll();

    @Query(value = "SELECT SUM (COALESCE (W.END_TIME, LOCALTIME) - W.START_TIME)" +
            " FROM WORK_LOG W WHERE W.DAY = ?1 AND W.EMPLOYEE = ?2 ",
            nativeQuery = true)
    String findTimeWorkByDayAndEmployeeId(LocalDate day, long employeeId);


    @Query(value = "SELECT SUM (COALESCE (W.END_TIME, LOCALTIME) - W.START_TIME)" +
            " FROM WORK_LOG W WHERE W.DAY BETWEEN ?1 AND ?2 AND W.EMPLOYEE = ?3",
            nativeQuery = true)
    String findAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, long employeeId);

    @Query("SELECT w FROM WorkLog w WHERE w.endTime = null")
    List<WorkLog> findEmployeeWhoWork();

    @Query(value = "SELECT * FROM WORK_LOG W WHERE W.END_TIME IS NULL AND W.EMPLOYEE = ?1",
            nativeQuery = true)
    WorkLog findByEmployeeId(long employeeId);

    @Query(value = "SELECT * FROM  WORK_LOG W WHERE W.EMPLOYEE = ?1 AND W.DAY BETWEEN ?2 AND ?3",
            nativeQuery = true)
    List<WorkLog> findAllWorkLogBetweenStartDateAndEndDateByEmployeeId(
            Long employeeId, LocalDate startDate, LocalDate endDate);
}