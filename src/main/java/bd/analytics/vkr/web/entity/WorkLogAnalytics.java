package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WorkLogAnalytics")
@Getter @Setter
@NoArgsConstructor
public class WorkLogAnalytics {

    enum types{week, month, year};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn
    private Employee employee;

    @Column
    private String dates;

    @Column
    private String duration;

    @Column
    private types type;

}
