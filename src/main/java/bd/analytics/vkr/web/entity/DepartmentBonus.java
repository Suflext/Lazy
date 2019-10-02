package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "DepartmentBonus")
@NoArgsConstructor
public class DepartmentBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @OneToOne (mappedBy = "department", optional=false, cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Department department;

    @Column
    @Getter @Setter
    private String dates;

    @Column
    @Getter @Setter
    private Long payout;

}
