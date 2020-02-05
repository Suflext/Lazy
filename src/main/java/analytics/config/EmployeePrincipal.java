package analytics.config;

import analytics.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class EmployeePrincipal implements UserDetails {

    private Employee employee;

    EmployeePrincipal(Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        return employee;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(employee.getRole()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return new BCryptPasswordEncoder().encode(employee.getPassword());
    }

    @Override
    public String getUsername() {
        return employee.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
