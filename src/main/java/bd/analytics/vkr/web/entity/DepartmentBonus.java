package bd.analytics.vkr.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DepartmentBonus")
@Data
@NoArgsConstructor
public class DepartmentBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dates;

    private Long payout;

    @OneToOne (cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Department department;

}
