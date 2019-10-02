package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "Employee")
@NoArgsConstructor
public class Employee {

    @Getter @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Getter @Setter
    @Column
    private String role;

    @Getter @Setter
    @Column
    private String login;

    @Getter @Setter
    @Column
    private String password;

    @Getter @Setter
    @Column
    private String firstName;

    @Getter @Setter
    @Column
    private String lastName;

    @Getter @Setter
    private Department department;

    @Getter @Setter
    private JobPosition jobPosition;

    @Getter @Setter
    private WorkLog workLogs;

    @Getter @Setter
    private WorkLogAnalytics workLogAnalytics;

}
