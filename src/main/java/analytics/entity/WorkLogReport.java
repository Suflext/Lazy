package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WORK_LOG_REPORT")
@Data
@NoArgsConstructor
public class WorkLogReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

//    private Long employee;

    private Date START_DATE;

    private Long duration;

    private ReportPeriodType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "id")
    private Employee employee;

    enum ReportPeriodType {week, month, year}

}
