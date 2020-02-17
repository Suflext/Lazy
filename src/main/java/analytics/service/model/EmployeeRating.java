package analytics.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeRating {
    private final long ratingId;
    private final String firstName;
    private final String lastName;
    private final String login;
    private final long time;
}
