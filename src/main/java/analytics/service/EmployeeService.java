package analytics.service;

import analytics.entity.Employee;
import analytics.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

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

    public void addStartDate(Date date, String login) {
//        Employee employee = employeeRepo.findByLogin(login);
//        Set<WorkLog> setWorkLogs = employee.getWorkLogs();
//        WorkLog workLog = new WorkLog();
//        workLog.setStartTime(date);
//        setWorkLogs.add(workLog);
//        employee.setWorkLogs(setWorkLogs);
//        employeeRepo.save(employee);
    }

    public void addEndDate(Date date, String login) {
//        Employee employee = employeeRepo.findByLogin(login);
//        Set<WorkLog> setWorkLogs = employee.getWorkLogs();
//        WorkLog workLog = new WorkLog();
//        workLog.setEndTime(date);
//        setWorkLogs.add(workLog);
//        employee.setWorkLogs(setWorkLogs);
//        employeeRepo.save(employee);
    }
}
