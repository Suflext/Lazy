package bd.analytics.vkr.web.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "SystemProperties")
@Getter @Setter
@NoArgsConstructor
public class SystemProperties {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String keys;

    @Column
    private String val;

    @Column
    private String description;
}
