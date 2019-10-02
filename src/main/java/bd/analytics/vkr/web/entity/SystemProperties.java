package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SystemProperties")
@NoArgsConstructor
public class SystemProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column
    @Getter @Setter
    private String keys;

    @Column
    @Getter @Setter
    private String val;

    @Column
    @Getter @Setter
    private String description;
}
