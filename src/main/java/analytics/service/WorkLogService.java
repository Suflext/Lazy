package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class WorkLogService {

    private final WorkLogRepository workLogRepo;

    private final WorkLogRepo workLogRe;

    public List<WorkLog> getAll() {
        return workLogRepo.findAll();
    }

    public void addStartDate(Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(LocalTime.now());
        workLog.setDay(now());
        workLog.setEmployee(employee);
        workLogRepo.save(workLog);
    }

    public void addEndDate(Employee employee) {
        WorkLog workLog = workLogRepo.findByEmployeeId(employee.getId());
        workLog.setEndTime(LocalTime.now());
        workLog.setDuration(between(workLog.getStartTime(), LocalTime.now()).getSeconds());
        workLogRepo.save(workLog);
    }

    public Long getTimeWorkByDayAndEmployeeId(LocalDate day, Long employeeId) {
        return workLogRe.getTimeWorkByDayAndEmployeeId(day, employeeId).getSeconds();
    }

    public long getLatecomerById(Employee employee){
        List<WorkLog> workLogs = workLogRepo.findAllBetweenTwoDateByEmployeeId(employee.getId(), now().withDayOfMonth(1), now());
        return workLogs.stream().filter(workLog -> workLog.getStartTime().isAfter(employee.getJobPosition().getStartTime())).count();
    }

    public List<WorkLog> getEmployeeWhoWork() {
        return workLogRepo.findEmployeeWhoWork();
    }

    public Map<LocalDate, Long> getListBetweenTwoDateByEmployee(Employee employee, LocalDate start, LocalDate end) {
        List<WorkLog> workLogs = workLogRepo.findAllBetweenTwoDateByEmployeeId(employee.getId(), start, end);

        Map<LocalDate, Long> longMap = new HashMap<>();
        Map<LocalDate, List<WorkLog>> listMap = workLogs.stream().collect(groupingBy(WorkLog::getDay));
        listMap.forEach((key, value) -> longMap.put(
                key,
                value.stream()
                        .mapToLong(workLog ->
                                between(workLog.getStartTime(),
                                        workLog.getEndTime() == null
                                                ? LocalTime.now()
                                                : workLog.getEndTime())
                                        .getSeconds()).sum()));
        return longMap;
    }
}
