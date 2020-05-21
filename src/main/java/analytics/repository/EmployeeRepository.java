package analytics.repository;

import analytics.entity.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

    Employee findByLogin(String login);

    List<Employee> findAll();

    @Query(value = "SELECT E.FIRST_NAME, E.LAST_NAME, E.LOGIN, W.ort FROM EMPLOYEE E LEFT JOIN " +
            "(SELECT userId AS id, SUM (totalTime) AS ort FROM " +
            "(SELECT SUM (COALESCE (L.END_TIME, LOCALTIME) - L.START_TIME) AS totalTime, L.EMPLOYEE AS userId FROM " +
            "WORK_LOG L WHERE L.DAY BETWEEN (LAST_DAY(CURRENT_DATE - 1 MONTH) + 1 DAY) AND " +
            "LAST_DAY(CURRENT_DATE) GROUP BY userId) GROUP BY ID) AS W " +
            "ON (E.ID = W.id) WHERE E.ID = W.id ORDER BY W.ort DESC",
            nativeQuery = true)
    List<String> findEmployeeRating();


    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE NOT E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAY = CURRENT_DATE AND L.END_TIME is null)",
            nativeQuery = true)
    List<Employee> findNotWorkList();

    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE NOT E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAY = CURRENT_DATE AND (L.END_TIME is null OR " +
            "(L.START_TIME is not null AND L.END_TIME is not null)))",
            nativeQuery = true)
    List<Employee> findNotComeToday();

    @Query(value = "SELECT E.* FROM EMPLOYEE E " +
            "WHERE E.ID IN (SELECT L.EMPLOYEE FROM WORK_LOG L WHERE L.DAY = CURRENT_DATE AND (L.END_TIME is null OR " +
            "(L.START_TIME is not null AND L.END_TIME is not null))) AND NOT E.ID IN (SELECT G.EMPLOYEE FROM WORK_LOG G " +
            "WHERE G.DAY = CURRENT_DATE AND G.START_TIME < '09:00:00.0000000')",
            nativeQuery = true)
    List<Employee> findLatecomers();
}