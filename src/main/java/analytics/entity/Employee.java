package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
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

    private Long department;

    private Long jobPosition;

//    @OneToMany(fetch = FetchType.LAZY)
//    private Set<WorkLog> workLogs = new HashSet<>();

}
