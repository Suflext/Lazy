package bd.analytics.vkr.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "WorkLogAnalytics")
@Data
@NoArgsConstructor
public class WorkLogAnalytics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long employee;

    private String dates;

    private String duration;

    private types type;

    enum types {week, month, year}

}
