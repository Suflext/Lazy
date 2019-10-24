package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class WorkLogService {

    private WorkLogRepository workLogRepo;

    public WorkLogService(WorkLogRepository workLogRepo) {
        this.workLogRepo = workLogRepo;
    }


    public ArrayList<WorkLog> findAll() {
        return new ArrayList<>(workLogRepo.findAll());
    }

    public void addStartDate(Date date, Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(date);
        workLog.setEmployee(employee);
        workLogRepo.save(workLog);
    }

    public void addEndDate(Date date, Employee employee) {
        WorkLog workLog = workLogRepo.findById(3);
        workLog.setEndTime(date);
        workLogRepo.save(workLog);

//        WorkLog workLog = workLogRepo.findByEmployeeLogin(employee.getLogin());
//        workLog.setEndTime(date);
//        workLogRepo.save(workLog);
    }
}
