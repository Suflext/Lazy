package analytics.config;

import analytics.entity.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeeDetailService implements UserDetailsService {

    private ArrayList<Employee> employees;

    public EmployeeDetailService(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = employees.stream().filter(u -> u.getLogin().equals(username)).findAny();
        if (!employee.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(employee.get());
    }

    private UserDetails toUserDetails(Employee employee) {
        return User
                .withUsername(employee.getLogin())
                .password(new BCryptPasswordEncoder().encode(employee.getPassword()))
                .roles(employee.getRole())
                .build();
    }

}
