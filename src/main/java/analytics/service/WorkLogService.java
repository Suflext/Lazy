package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
@Service
public class WorkLogService {

    private WorkLogRepository workLogRepo;
    private EmployeeRepository employeeRepo;

    public WorkLogService(WorkLogRepository workLogRepo, EmployeeRepository employeeRepo) {
        this.workLogRepo = workLogRepo;
        this.employeeRepo = employeeRepo;
    }

    public ArrayList<WorkLog> findAll() {
        return new ArrayList<>(workLogRepo.findAll());
    }

    public void addStartDate(LocalTime date, Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(date);
        workLog.setDaily(LocalDate.now());
        workLog.setEmployee(employee);
        workLogRepo.save(workLog);
    }

    public void addEndDate(LocalTime date, Employee employee) {
        ArrayList<WorkLog> list = workLogRepo.findAllByDailyAndEmployee(LocalDate.now(), employee.getId());
        list.sort(WorkLog.COMPARE_BY_ID);
        WorkLog workLog = list.get(list.size() - 1);
        workLog.setEndTime(date);
        workLog.setDuration(Duration.between(workLog.getStartTime(), date).getSeconds());
        workLogRepo.save(workLog);
    }

    public Long getDuration(Employee employee, LocalDate localDate) {
        long duration = 0;
        for (WorkLog workLog : workLogRepo.findAllByDailyAndEmployee(localDate, employee.getId())) {
            if (workLog.getEndTime() != null)
                duration += workLog.getDuration();
            else
                duration += Duration.between(workLog.getStartTime(), LocalTime.now()).getSeconds();
        }
        return duration;
    }

    public String getStringFormatDuration(long duration) {
        long minutes = 0, hours = 0;
        if (duration >= 60) {
            minutes = duration / 60;
            duration -= minutes * 60;
        }
        if (minutes >= 60) {
            hours = minutes / 60;
            minutes -= hours * 60;
        }
        return hours + " hours " + minutes + " minutes " + duration + " seconds";
    }

    public ArrayList<WorkLog> getActiveEmployee() {
        return workLogRepo.findByEndTime();
    }

    public Collection<Employee> getRatingEmployee() {
        TreeMap<Long, Employee> sorted = new TreeMap<>(Collections.reverseOrder());
        for (Employee employee : employeeRepo.findAll()) {
            long duration = 0;
            for (WorkLog workLog : workLogRepo.findAllByEmployeeId(employee.getId())) {
                if (workLog.getEndTime() != null)
                    duration += workLog.getDuration();
                else
                    duration += Duration.between(workLog.getStartTime(), LocalTime.now()).getSeconds();
            }
            sorted.put(duration, employee);
        }
        return sorted.values();
    }
}
