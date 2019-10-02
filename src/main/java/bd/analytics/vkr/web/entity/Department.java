package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Department")
@NoArgsConstructor
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @Column
    @Getter @Setter
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Set<Employee> employees = new HashSet<>();

    @Getter @Setter
    private DepartmentBonus departmentBonus;

}
