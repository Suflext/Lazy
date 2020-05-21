package analytics.service.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WeeklyReport {
    private final long id;
    private final String department;
    private final long time;
}
