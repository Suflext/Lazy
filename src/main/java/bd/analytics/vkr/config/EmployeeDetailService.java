package bd.analytics.vkr.config;

import bd.analytics.vkr.web.dto.ClientConvert;
import bd.analytics.vkr.web.entity.Client;
import bd.analytics.vkr.web.entity.Employee;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Optional;

public class EmployeeDetailService implements UserDetailsService {

    private ArrayList<Client> clients = new ArrayList<>();

    public EmployeeDetailService(ArrayList<Employee> employees) {
        clients.addAll(new ClientConvert().convert(employees));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Client> employee = clients.stream().filter(u -> u.getName().equals(username)).findAny();
        if (!employee.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(employee.get());
    }

    private UserDetails toUserDetails(Client employee) {
        return User
                .withUsername(employee.getName())
                .password(new BCryptPasswordEncoder().encode(employee.getPassword()))
                .roles(employee.getRole())
                .build();
    }

}
