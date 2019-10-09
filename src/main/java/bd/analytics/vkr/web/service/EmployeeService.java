package bd.analytics.vkr.web.service;

import bd.analytics.vkr.web.entity.Employee;
import bd.analytics.vkr.web.repository.EmployeeRepository;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public void add() {
        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setRole("USER");
        employee.setLogin("user");
        employee.setPassword("us");
        employeeRepo.save(employee);
    }

    public List findAll() {
        return new ArrayList<>(employeeRepo.findAll());
    }

}
