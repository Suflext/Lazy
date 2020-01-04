package analytics.repository;

import analytics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLogin(String login);

    ArrayList<Employee> findAll();

    @Query(value = "SELECT E.*, W.ort FROM EMPLOYEE E LEFT JOIN " +
            "(SELECT userId AS id, SUM (totalTime) AS ort FROM " +
            "(SELECT SUM (COALESCE (L.END_TIME, LOCALTIME) - L.START_TIME) AS totalTime, L.EMPLOYEE AS userId FROM " +
            "WORK_LOG L WHERE L.DAILY BETWEEN (LAST_DAY(CURRENT_DATE - 1 MONTH) + 1 DAY) AND " +
            "LAST_DAY(CURRENT_DATE) GROUP BY userId) GROUP BY ID) AS W " +
            "ON (E.ID = W.id) WHERE E.ID = W.id ORDER BY W.ort DESC",
            nativeQuery = true)
    ArrayList<Employee> findEmployeeBySumDuration();
}