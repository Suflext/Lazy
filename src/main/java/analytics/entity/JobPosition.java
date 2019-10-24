package analytics.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "JOB_POSITION")
@Data
@NoArgsConstructor
public class JobPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long weekHours;

    private LocalTime startTime;

    private Long salary;

}
