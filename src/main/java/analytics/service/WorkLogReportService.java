package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLogReport;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogReportRepository;
import analytics.repository.WorkLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static analytics.entity.type.ReportPeriodType.reportPeriodType.WEEK;
import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.now;

@Service
@RequiredArgsConstructor
public class WorkLogReportService {

    private final WorkLogReportRepository workLogReportRepo;

    private final WorkLogRepository workLogRepo;

    private final WorkLogRepo workLogRe;

    private final EmployeeRepository employeeRepo;

    private static final String CRON = "*/10 * * * * *";

    public List<WorkLogReport> getAll() {
        return workLogReportRepo.findAll();
    }

    public Long getAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, Long employeeId) {
        return workLogRe.getAllTimeWorkBetweenTwoDatesByEmployeeId(startDate, endDate, employeeId).getSeconds();
    }

    @Scheduled(cron = CRON)
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
        long time = workLogRe.sumAllByEmployeeInPeriod(localDate, localDate.plusDays(7), employee.getId()).getSeconds();
        if (time == 0) return;
        WorkLogReport workLogReport = new WorkLogReport();
        workLogReport.setStartDate(localDate);
        workLogReport.setEmployee(employee);
        workLogReport.setType(WEEK);
        workLogReport.setDuration(time);
        workLogReportRepo.save(workLogReport);
    }
}
