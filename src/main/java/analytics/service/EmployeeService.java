package analytics.service;

import analytics.General;
import analytics.entity.Employee;
import analytics.repository.EmployeeRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService extends General {

    private EmployeeRepository employeeRepo;

    public EmployeeService(EmployeeRepository employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    @Getter
    @Setter
    private static class Format {
        private long id;
        private String firstName;
        private String lastName;
        private String login;
        private String time;
    }

    public List<Employee> getAll() {
        return employeeRepo.findAll();
    }

    public List<Format> getRatingEmployee() {
        int i = 0;
        List<Format> formats = new ArrayList<>();
        for (String item : employeeRepo.findEmployeeRating()) {
            Format format = new Format();
            format.setId(++i);
            format.setFirstName(item.split(",")[0]);
            format.setLastName(item.split(",")[1]);
            format.setLogin(item.split(",")[2]);
            format.setTime(getTime(item.split(",")[3]));
            formats.add(format);
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
