package analytics.repository;

import analytics.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("Select e from Employee e where e.login = ?1")
    Employee findByLogin(String login);

    ArrayList<Employee> findAll();
}
