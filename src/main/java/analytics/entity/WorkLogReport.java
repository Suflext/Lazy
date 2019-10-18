package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WORK_LOG_REPORT")
@Data
@NoArgsConstructor
public class WorkLogReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long employee;

    private String dates;

    private String duration;


    private ReportPeriodType type;

    enum ReportPeriodType {week, month, year}

}
