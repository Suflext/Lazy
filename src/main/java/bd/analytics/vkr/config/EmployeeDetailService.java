package bd.analytics.vkr.config;

import bd.analytics.vkr.web.entity.Employee;
import bd.analytics.vkr.web.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class EmployeeDetailService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepo;

    @Override
    public UserDetails loadUserByUsername(String login) {
        Employee employee = employeeRepo.findByLogin(login);
        if (employee == null)throw new UsernameNotFoundException(login);
        return new EmployeePrincipal(employee);
    }
}
