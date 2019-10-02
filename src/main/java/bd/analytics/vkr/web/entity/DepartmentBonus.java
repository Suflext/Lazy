package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DepartmentBonus")
@Getter @Setter
@NoArgsConstructor
public class DepartmentBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne (mappedBy = "departmentBonus", optional=false, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;

    @Column
    private String dates;

    @Column
    private Long payout;

}
