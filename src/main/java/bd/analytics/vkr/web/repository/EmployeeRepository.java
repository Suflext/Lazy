package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Employee findByLogin(String login);

    //List<Employee> findAll();

}
