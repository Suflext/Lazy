package bd.analytics.vkr.web.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Client {
    private String name;
    private String password;
    private String role;

}
