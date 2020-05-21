package analytics.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class viewSumAward {
    private final long id;
    private final long employeeId;
    private final String firstName;
    private final String lastName;
    private final String jobPosition;
    private final String department;
    private final long award;
}
