package analytics.service;

import analytics.config.Decorator;
import analytics.entity.Employee;
import analytics.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class WorkLogRepo extends Decorator {

    private final WorkLogRepository workLogRepo;

    public Duration getTimeWorkByDayAndEmployeeId(LocalDate day, Employee employee) {
        return getDuration(workLogRepo.findTimeWorkByDayAndEmployeeId(day, employee));
    }

    public Duration getAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, Employee employee) {
        System.out.println(workLogRepo.sumAllByEmployeeInPeriod(startDate, endDate, employee));
        return getDuration(workLogRepo.sumAllByEmployeeInPeriod(startDate, endDate, employee));
    }

    public Duration sumAllByEmployeeInPeriod(LocalDate localDate, LocalDate plusDays, Employee employee) {
        String time = workLogRepo.sumAllByEmployeeInPeriod(localDate, plusDays, employee);
        if (time == null) return Duration.ZERO;
        else return getDuration(time);
    }
}
