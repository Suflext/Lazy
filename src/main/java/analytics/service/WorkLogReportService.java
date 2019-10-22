package analytics.service;

import analytics.entity.WorkLogReport;
import analytics.repository.WorkLogReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkLogReportService {

    private WorkLogReportRepository workLogRepo;

    public WorkLogReportService(WorkLogReportRepository workLogReportRepo) {
        this.workLogRepo = workLogReportRepo;
    }


    public ArrayList<WorkLogReport> findAll() {
        return new ArrayList<>(workLogRepo.findAll());
    }
}
