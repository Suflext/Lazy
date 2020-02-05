package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.now;

@Service
public class WorkLogService {

    private WorkLogRepository workLogRepo;

    public WorkLogService(WorkLogRepository workLogRepo) {
        this.workLogRepo = workLogRepo;
    }

    public List<WorkLog> findAll() {
        return new ArrayList<>(workLogRepo.findAll());
    }

    public void addStartDate(Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(LocalTime.now());
        workLog.setDaily(now());
        workLog.setEmployee(employee);
        workLogRepo.save(workLog);
    }

    public void addEndDate(Employee employee) {
        WorkLog workLog = workLogRepo.findByDailyAndEmployeeAndOrder(employee.getId());
        workLog.setEndTime(LocalTime.now());
        workLog.setDuration(Duration.between(workLog.getStartTime(), LocalTime.now()).getSeconds());
        workLogRepo.save(workLog);
    }

    public Long getDuration(Employee employee, LocalDate localDate) {
        return Duration.between(LocalTime.parse("00:00:00"), LocalTime.parse(workLogRepo.findAllByDailyAndEmployee(localDate, employee.getId()).split(" ")[1])).getSeconds();
    }

    public List<WorkLog> getActiveEmployee() {
        return workLogRepo.findEndTimeIsNull();
    }

    public List<WorkLog> getListStartWorkWeekByEmployeeId(Long employeeId) {
        LocalDate start = now().with(MONDAY), end = now();
        ArrayList<WorkLog> workLogs = workLogRepo.findAllWorkLogBetweenStartDateAndEndDateByEmployeeId(employeeId, start, end);
        List<WorkLog> min = new ArrayList<>();
        do {
            final LocalDate date = start;
            min.add(workLogs
                    .stream()
                    .filter(workLog -> workLog.getDaily().equals(date))
                    .min(Comparator.comparing(WorkLog::getStartTime))
                    .orElseGet(WorkLog::new));

            start = start.plusDays(1);
        } while (!start.isAfter(end));
        return min;
    }

    public List<WorkLog> getListFinishWorkWeekByEmployeeId(Long employeeId) {
        LocalDate start = now().with(MONDAY), end = now();
        ArrayList<WorkLog> workLogs = workLogRepo.findAllWorkLogBetweenStartDateAndEndDateByEmployeeId(employeeId, start, end);
        List<WorkLog> max = new ArrayList<>();
        do {
            final LocalDate date = start;
            max.add(workLogs
                    .stream()
                    .filter(workLog -> workLog.getDaily().equals(date))
                    .max(Comparator.comparing(WorkLog::getStartTime))
                    .orElseGet(WorkLog::new));

            start = start.plusDays(1);
        } while (!start.isAfter(end));
        return max;
    }
}
