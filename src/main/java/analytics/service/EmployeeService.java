package analytics.service;

import analytics.entity.Employee;
import analytics.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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

    public ArrayList<Employee> findAll() {
        return new ArrayList<>(employeeRepo.findAll());
    }

    public String findNameByLogin(String login) {
        return employeeRepo.findByLogin(login).getFirstName();
    }

    public Employee findByLogin(String login) {
        return employeeRepo.findByLogin(login);
    }

}
