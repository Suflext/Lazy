package analytics.service;

import analytics.config.Decorator;
import analytics.entity.Employee;
import analytics.repository.EmployeeRepository;
import analytics.service.model.EmployeeRating;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService extends Decorator {

    private EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    public List<EmployeeRating> getRatingEmployee() {
        int i = 0;
        List<EmployeeRating> formats = new ArrayList<>();
        for (String item : employeeRepo.findEmployeeRating()) {
            EmployeeRating employeeRating = EmployeeRating.builder()
                    .ratingId(++i)
                    .firstName(item.split(",")[0])
                    .lastName(item.split(",")[1])
                    .login(item.split(",")[2])
                    .time(convert(item.split(",")[3]).getSeconds()).build();
            formats.add(employeeRating);
        }
        return formats;
    }

    public List<Employee> getNotWorkList() {
        return employeeRepo.findNotWorkList();
    }

    public List<Employee> getNotComeToday() {
        return employeeRepo.findNotComeToday();
    }

    public List<Employee> getLatecomers() {
        return employeeRepo.findLatecomers();
    }
}
