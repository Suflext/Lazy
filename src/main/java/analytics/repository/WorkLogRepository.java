package analytics.repository;

import analytics.entity.WorkLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface WorkLogRepository extends JpaRepository<WorkLog, Long> {

    ArrayList<WorkLog> findAll();

    //@Query("SELECT * FROM WORK_LOG w WHERE w.EMPLOYEE.LOGIN = ?")
//    WorkLog findByEmployeeLogin(String login);

    WorkLog findById(long id);
}
