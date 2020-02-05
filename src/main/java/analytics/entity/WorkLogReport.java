package analytics.entity;

import analytics.General.ReportPeriodType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "WORK_LOG_REPORT")
@Data
@NoArgsConstructor
public class WorkLogReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate startDate;

    private Long duration;

    private ReportPeriodType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "id")
    private Employee employee;

}
