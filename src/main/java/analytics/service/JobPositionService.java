package analytics.service;

import analytics.repository.JobPositionRepository;
import org.springframework.stereotype.Service;

@Service
public class JobPositionService {

    private JobPositionRepository jobPositionRepository;

    public JobPositionService(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }


}
