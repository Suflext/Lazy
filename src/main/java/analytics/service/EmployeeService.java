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

    public ArrayList<Employee> findAll() {
        return new ArrayList<>(employeeRepo.findAll());
    }

    public ArrayList<Employee> getRatingEmployee() {
        return employeeRepo.findEmployeeBySumDuration();
    }
}
