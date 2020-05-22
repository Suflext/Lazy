package analytics.service.model;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class viewAward {
    private final long id;
    private final LocalDate date;
    private final String typeDay;
    private final String absence;
    private final String lateness;
    private final long overwork;
    private final long award;
}
