/*package analytics;

import analytics.entity.Employee;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserDetails {

    private UserDetails userDetails;

    private Employee employee;

    public MyUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public String getUsername(){
        return userDetails.getUsername();
    }

    public String getPassword(){
        return userDetails.getPassword();
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}*/


package analytics;

import analytics.entity.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MyUserDetails extends User {

    private Employee employee;

    public MyUserDetails(String username, Collection<? extends GrantedAuthority> authorities) {
        super(username, "", authorities);
    }

    public void setEmployee(Employee employee){
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}


