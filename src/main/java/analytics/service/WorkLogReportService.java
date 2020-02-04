package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLogReport;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogReportRepository;
import analytics.repository.WorkLogRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class WorkLogReportService {

    private WorkLogReportRepository workLogReportRepo;

    private WorkLogRepository workLogRepo;

    private EmployeeRepository employeeRepo;

    private final String cron = "*/10 * * * * *";

    public WorkLogReportService(WorkLogReportRepository workLogReportRepo, WorkLogRepository workLogRepo, EmployeeRepository employeeRepo) {
        this.workLogReportRepo = workLogReportRepo;
        this.workLogRepo = workLogRepo;
        this.employeeRepo = employeeRepo;
    }

    public ArrayList<WorkLogReport> findAll() {
        return new ArrayList<>(workLogReportRepo.findAll());
    }

    public Long timeWorkUp(String type, LocalDate localDate, Employee employee) {
        int plus = 0;
        if (type.equals("week")) plus = 7;
        else if (type.equals("month")) plus = localDate.lengthOfMonth();
        return Duration.between(LocalTime.parse("00:00:00"), LocalTime.parse(workLogRepo.findAllByDaily(localDate, localDate.plusDays(plus), employee.getId()).split(" ")[1])).getSeconds();
    }

    @Scheduled(cron = cron)
    public void writerWeek() {
        LocalDate localDate = LocalDate.now().with(DayOfWeek.MONDAY);
        for (Employee employee : employeeRepo.findAll()) {

//                spring data jpa pageable read
            try {
                WorkLogReport workLogReport = new WorkLogReport();
                workLogReport.setStartDate(localDate);
                workLogReport.setEmployee(employee);
                workLogReport.setType(WorkLogReport.ReportPeriodType.week);
                workLogReport.setDuration(Duration.between(LocalTime.parse("00:00:00"), LocalTime.parse(workLogRepo.findAllByDaily(localDate, localDate.plusDays(7), employee.getId()).split(" ")[1])).getSeconds());workLogReportRepo.save(workLogReport);
            } catch (Exception e) {}
        }
    }
    //отсчитывать время без работы пользователя и например если пользователь не трогал клавиатуру в теч часа
    //то можно выключать
}
