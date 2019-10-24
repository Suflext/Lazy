package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "EMPLOYEE")
@Data
@ToString(of = {"id", "role", "login", "password", "firstName", "lastName"})

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTMENT", referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "JOB_POSITION", referencedColumnName = "id")
    private JobPosition jobPosition;

}
