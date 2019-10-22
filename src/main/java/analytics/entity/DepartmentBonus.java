package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTMENT_BONUS")
@Data
@NoArgsConstructor
public class DepartmentBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String dates;

    private Long payout;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

}
