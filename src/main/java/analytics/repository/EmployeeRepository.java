package analytics.repository;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLogin(String login);

    ArrayList<Employee> findAll();

    @Query(value = "SELECT E.FIRST_NAME, E.LAST_NAME, E.LOGIN, W.ort FROM EMPLOYEE E LEFT JOIN " +
            "(SELECT userId AS id, SUM (totalTime) AS ort FROM " +
            "(SELECT SUM (COALESCE (L.END_TIME, LOCALTIME) - L.START_TIME) AS totalTime, L.EMPLOYEE AS userId FROM " +
            "WORK_LOG L WHERE L.DAILY BETWEEN (LAST_DAY(CURRENT_DATE - 1 MONTH) + 1 DAY) AND " +
            "LAST_DAY(CURRENT_DATE) GROUP BY userId) GROUP BY ID) AS W " +
            "ON (E.ID = W.id) WHERE E.ID = W.id ORDER BY W.ort DESC",
            nativeQuery = true)
    ArrayList<String> findEmployeeBySumDuration();


    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE NOT E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAILY = CURRENT_DATE AND L.END_TIME is null)",
            nativeQuery = true)
    ArrayList<Employee> findNotWorkList();

    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE NOT E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAILY = CURRENT_DATE AND (L.END_TIME is null OR " +
            "(L.START_TIME is not null AND L.END_TIME is not null)))",
            nativeQuery = true)
    ArrayList<Employee> findNotComeToday();

    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAILY = ?1 AND (L.END_TIME is null OR " +
            "(L.START_TIME is not null AND L.END_TIME is not null))) AND NOT E.ID IN (SELECT G.EMPLOYEE FROM WORK_LOG G " +
            "WHERE G.DAILY = ?1 AND G.START_TIME < '09:00:00.0000000')",
            nativeQuery = true)
    ArrayList<Employee> findLatecomers(LocalDate date);

}