package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLogin(String login);

    ArrayList<Employee> findAll();
}
