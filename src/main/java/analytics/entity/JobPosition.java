package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JOB_POSITION")
@Data
@NoArgsConstructor
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long monthHours;

    private Date startTime;

    private Long salary;

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Employee> employees = new HashSet<>();

}
