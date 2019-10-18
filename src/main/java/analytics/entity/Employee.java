package analytics.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EMPLOYEE")
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"id", "role"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String role;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private Department department;

    private JobPosition jobPosition;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <WorkLog> workLogs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <WorkLogReport> workLogAnalytics = new HashSet<>();

}
