package bd.analytics.vkr.web.service;

import bd.analytics.vkr.web.entity.Employee;
import bd.analytics.vkr.web.entity.WorkLogAnalytics;
import bd.analytics.vkr.web.repository.DepartmentRepository;
import bd.analytics.vkr.web.repository.EmployeeRepository;
import bd.analytics.vkr.web.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepo;
    private PositionRepository positionRepo;
    private DepartmentRepository departmentRepo;

    public EmployeeService(EmployeeRepository employeeRepo, PositionRepository positionRepo, DepartmentRepository departmentRepo) {
        this.employeeRepo = employeeRepo;
        this.positionRepo = positionRepo;
        this.departmentRepo = departmentRepo;
    }

    public void add() {
        Employee employee = new Employee();
        employee.setFirstName("Joe");
        employee.setLogin("user");
        employee.setPassword("us");
        employee.setDepartment(departmentRepo.findById(1L));
        employee.setJobPosition(positionRepo.findById(1L));
        employee.setId(1);
        employeeRepo.save(employee);
    }

    public List findAll() {
        return new ArrayList<>(employeeRepo.findAll());
    }

}
