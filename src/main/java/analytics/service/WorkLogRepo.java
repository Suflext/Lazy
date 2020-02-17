package analytics.service;

import analytics.config.Decorator;
import analytics.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkLogRepo extends Decorator {

    private final WorkLogRepository workLogRepo;

    public Duration getTimeWorkByDayAndEmployeeId(LocalDate day, Long employeeId) {
        return convert(workLogRepo.findTimeWorkByDayAndEmployeeId(day, employeeId));
    }

    public Duration getAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, Long employeeId) {
        return convert(workLogRepo.sumAllByEmployeeInPeriod(startDate, endDate, employeeId));
    }

    public Duration sumAllByEmployeeInPeriod(LocalDate localDate, LocalDate plusDays, long id) {
        String time = workLogRepo.sumAllByEmployeeInPeriod(localDate, plusDays, id);
        if (time == null) return Duration.ZERO;
        else return convert(time);
    }
}
