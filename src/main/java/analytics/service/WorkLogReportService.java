package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLogReport;
import analytics.repository.WorkLogReportRepository;
import analytics.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

@Service
public class WorkLogReportService {

    private WorkLogReportRepository workLogReportRepo;

    private WorkLogRepository workLogRepo;

    public WorkLogReportService(WorkLogReportRepository workLogReportRepo, WorkLogRepository workLogRepo) {
        this.workLogReportRepo = workLogReportRepo;
        this.workLogRepo = workLogRepo;
    }

    public ArrayList<WorkLogReport> findAll() {
        return new ArrayList<>(workLogReportRepo.findAll());
    }

    public Long timeWorkUp(String type, LocalDate localDate, Employee employee){
        int plus= 0;
        if (type.equals("week")) plus = 7;
        else if (type.equals("month")) plus = localDate.lengthOfMonth();
        else plus = localDate.lengthOfYear();
        return Duration.between(LocalTime.parse("00:00:00"), LocalTime.parse(workLogRepo.findAllByDaily(localDate, localDate.plusDays(plus), employee.getId()).split(" ")[1])).getSeconds();
    }
}
