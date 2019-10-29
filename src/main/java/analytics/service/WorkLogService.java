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

    public void addStartDate(Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(LocalTime.now());
        workLog.setDaily(LocalDate.now());
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

}
