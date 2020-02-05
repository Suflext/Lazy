package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLogReport;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogReportRepository;
import analytics.repository.WorkLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static analytics.General.ReportPeriodType.week;
import static java.time.DayOfWeek.MONDAY;
import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.time.LocalTime.parse;

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

    public List<WorkLogReport> getAll() {
        return workLogReportRepo.findAll();
    }

    public Long getAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, Long employeeId) {
        return between(parse("00:00:00"), parse(workLogRepo.findAllTimeWorkBetweenTwoDatesByEmployeeId(
                startDate,
                endDate,
                employeeId).split(" ")[1])).getSeconds();
    }

    @Scheduled(cron = cron)
    public void weeklyReport() {
        Pageable pageRequestOfEmployee = PageRequest.of(0, 1000);
        Page<Employee> pageOfEmployee;
        do {
            pageOfEmployee = employeeRepo.findAll(pageRequestOfEmployee);
            pageOfEmployee.getContent().forEach(this::recordingWorkLogReportByEmployee);
            pageRequestOfEmployee = pageOfEmployee.nextPageable();
        } while (pageOfEmployee.hasNext());
    }

    private void recordingWorkLogReportByEmployee(Employee employee) {
        LocalDate localDate = now().with(MONDAY);
        String time = workLogRepo.findAllTimeWorkBetweenTwoDatesByEmployeeId(localDate, localDate.plusDays(7), employee.getId());
        if (time == null) return;
        WorkLogReport workLogReport = new WorkLogReport();
        workLogReport.setStartDate(localDate);
        workLogReport.setEmployee(employee);
        workLogReport.setType(week);
        workLogReport.setDuration(between(parse("00:00:00"),
                parse(time.split(" ")[1])).getSeconds());
        workLogReportRepo.save(workLogReport);
    }
}
