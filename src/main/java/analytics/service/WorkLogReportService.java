package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLogReport;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogReportRepository;
import analytics.service.model.WeeklyReport;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static analytics.entity.type.ReportPeriodType.reportPeriodType.WEEK;
import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class WorkLogReportService {

    private final WorkLogReportRepository workLogReportRepo;

    private final WorkLogRepo workLogRe;

    private final EmployeeRepository employeeRepo;

    //    private static final String CRON = "0 0 0 1 * ?"; // At 0 am on the first day of every month
    //    private static final String CRON = "0 0 23 ? * SUN";
    private static final String CRON = "*/20 * * * * *";

    public List<WorkLogReport> getAll() {
        return workLogReportRepo.findAll();
    }

    public Long getAllTimeWorkBetweenTwoDatesByEmployeeId(LocalDate startDate, LocalDate endDate, Employee employee) {
        return workLogRe.getAllTimeWorkBetweenTwoDatesByEmployeeId(startDate, endDate, employee).getSeconds();
    }

    @Scheduled(cron = CRON)
    public void monthlyReport() {
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
        long time = workLogRe.sumAllByEmployeeInPeriod(localDate, now(), employee).getSeconds();
        if (time == 0) return;
        WorkLogReport workLogReport = new WorkLogReport();
        workLogReport.setStartDate(localDate);
        workLogReport.setEmployee(employee);
        workLogReport.setType(WEEK);
        workLogReport.setDuration(time);
        workLogReportRepo.save(workLogReport);
    }

    public List<WeeklyReport> getAllByStartDate() {
        List<WorkLogReport> reports = workLogReportRepo.findAllByStartDate(now().with(MONDAY));

        Map<String, List<WorkLogReport>> listMap = reports.stream().collect(groupingBy(report -> report.getEmployee().getDepartment().getName()));

        List<WeeklyReport> list = new ArrayList<>();
        AtomicInteger i = new AtomicInteger();
        listMap.forEach((s, workLogReports) -> {
            list.add(WeeklyReport.builder()
                    .id(i.incrementAndGet())
                    .department(s)
                    .time(workLogReports.stream().mapToLong(WorkLogReport::getDuration).sum()).build());
        });

        return list;
    }
}
