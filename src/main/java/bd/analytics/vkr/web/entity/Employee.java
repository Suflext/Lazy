package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Employee")
@Getter @Setter
@NoArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String role;

    private String login;

    private String password;

    private String firstName;

    private String lastName;

    private long department;

    private long jobPosition;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <WorkLog> workLogs = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set <WorkLogAnalytics> workLogAnalytics = new HashSet<>();

}
