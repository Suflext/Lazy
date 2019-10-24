package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;

@Entity
@Table(name = "WORK_LOG")
@Data
@NoArgsConstructor
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate daily;

    private LocalTime startTime;

    private LocalTime endTime;

    private Long duration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE", referencedColumnName = "id")
    private Employee employee;

    public static final Comparator<WorkLog> COMPARE_BY_ID = new Comparator<WorkLog>() {
        @Override
        public int compare(WorkLog lhs, WorkLog rhs) {
            return (int)(lhs.getId() - rhs.getId());
        }
    };

}