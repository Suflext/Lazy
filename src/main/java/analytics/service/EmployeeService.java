package analytics.service;

import analytics.config.Decorator;
import analytics.config.Security;
import analytics.entity.Employee;
import analytics.repository.EmployeeRepository;
import analytics.service.model.EmployeeRating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService extends Decorator {

    private final EmployeeRepository employeeRepo;

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    public List<EmployeeRating> getRatingEmployee() {
        int i = 0;
        List<EmployeeRating> ratingList = new ArrayList<>();
        for (String item : employeeRepo.findEmployeeRating()) {
            EmployeeRating employeeRating = EmployeeRating.builder()
                    .ratingId(++i)
                    .firstName(item.split(",")[0])
                    .lastName(item.split(",")[1])
                    .login(item.split(",")[2])
                    .time(getDuration(item.split(",")[3]).getSeconds()).build();
            ratingList.add(employeeRating);
        }
        return ratingList;
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

    public void add(Employee employee) {
        long maxId = employeeRepo.findAll().size();
        employee.setId(maxId + 1);
        employee.setPassword(new Security().passwordEncoder().encode(employee.getPassword()));

        employeeRepo.save(employee);
     }

    public Employee findById(Long id) {
        return employeeRepo.findById(id).get();
    }
}
