package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "WorkLog")
@NoArgsConstructor
public class WorkLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @OneToOne(mappedBy = "workLogs", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Employee employee;

    @Column
    @Getter @Setter
    private Date startTime;

    @Column
    @Getter @Setter
    private String endTime;

    @Column
    @Getter @Setter
    private String duration;

}
