package bd.analytics.vkr.web.service;

import bd.analytics.vkr.web.entity.WorkLog;
import bd.analytics.vkr.web.repository.WorkLogRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class WorkLogService {

    private WorkLogRepository workLogRepository;

    public WorkLogService(WorkLogRepository workLogRepository) {
        this.workLogRepository = workLogRepository;
    }


    public ArrayList<WorkLog> findAll() {
        return new ArrayList<>(workLogRepository.findAll());
    }
}
