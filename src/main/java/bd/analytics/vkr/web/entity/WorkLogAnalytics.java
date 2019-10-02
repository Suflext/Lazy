package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "WorkLogAnalytics")
@NoArgsConstructor
public class WorkLogAnalytics {

    enum types{week, month, year};

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private long id;

    @OneToOne(mappedBy = "workLogAnalytics", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Getter @Setter
    private Employee employee;

    @Column
    @Getter @Setter
    private String dates;

    @Column
    @Getter @Setter
    private String duration;

    @Column
    @Getter @Setter
    private types type;

}
