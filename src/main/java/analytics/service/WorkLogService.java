package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.time.DayOfWeek.MONDAY;
import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.time.LocalTime.*;

@Service
public class WorkLogService {

    private WorkLogRepository workLogRepo;

    public WorkLogService(WorkLogRepository workLogRepo) {
        this.workLogRepo = workLogRepo;
    }

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
        return between(parse("00:00:00"), parse(workLogRepo.findTimeWorkByDayAndEmployeeId(
                day,
                employeeId).split(" ")[1])).getSeconds();
    }

    public List<WorkLog> getEmployeeWhoWork() {
        return workLogRepo.findEmployeeWhoWork();
    }

    public List<WorkLog> getListStartAndFinishWorkWeekByEmployeeId(Long employeeId) {
        LocalDate start = now().with(MONDAY), end = now();
        List<WorkLog> workLogs = workLogRepo.findAllWorkLogBetweenStartDateAndEndDateByEmployeeId(employeeId, start, end);
        List<WorkLog> startTimeWork = new ArrayList<>(), finishTimeWork = new ArrayList<>();

        do {
            final LocalDate date = start;
            startTimeWork.add(workLogs
                    .stream()
                    .filter(workLog -> workLog.getDay().equals(date))
                    .min(Comparator.comparing(WorkLog::getStartTime))
                    .orElseGet(WorkLog::new));

            finishTimeWork.add(workLogs
                    .stream()
                    .filter(workLog -> workLog.getDay().equals(date))
                    .max(Comparator.comparing(WorkLog::getStartTime))
                    .orElseGet(WorkLog::new));

            start = start.plusDays(1);
        } while (!start.isAfter(end));

        List<WorkLog> list = new ArrayList<>(startTimeWork);
        list.addAll(finishTimeWork);
        return list;
    }
}
