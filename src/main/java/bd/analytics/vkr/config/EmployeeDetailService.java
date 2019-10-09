package bd.analytics.vkr.config;

import bd.analytics.vkr.web.entity.Employee;
import bd.analytics.vkr.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepo;
    private ArrayList<Employee> employees = new ArrayList<>();

    private void init() {
        Employee employee = new Employee();
        employee.setLogin("null");
        employees.add(employee);
        employees.addAll(employeeRepo.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        init();
        Optional<Employee> employee = employees.stream().filter(u -> u.getLogin().equals(username)).findAny();
        if (!employee.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(employee.get());
    }

    private UserDetails toUserDetails(Employee employee) {
        return User
                .withUsername(employee.getLogin())
                .password(employee.getPassword())
                .roles(employee.getRole())
                .build();
    }

}
