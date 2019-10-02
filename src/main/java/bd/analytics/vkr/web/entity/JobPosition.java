package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "JobPosition")
@NoArgsConstructor
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String monthHours;

    @Column
    @Getter @Setter
    private String startTime; //timestamp

    @Column
    @Getter @Setter
    private Long salory;

    @OneToMany(mappedBy = "positions", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<Employee> employees = new HashSet<>();

}
