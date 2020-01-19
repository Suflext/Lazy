package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public ArrayList<String> getRatingEmployee() {
        return employeeRepo.findEmployeeBySumDuration();
    }

    public ArrayList<Employee> getNotWorkList() {
        return employeeRepo.findNotWorkList();
    }

    public ArrayList<Employee> getNotComeToday() {
        return employeeRepo.findNotComeToday();
    }

    public ArrayList<Employee> getLatecomers(LocalDate date) {
        return employeeRepo.findLatecomers(date);
    }
}
