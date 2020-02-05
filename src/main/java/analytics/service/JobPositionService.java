package analytics.service;

import analytics.repository.JobPositionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobPositionService {

    private JobPositionRepository jobPositionRepository;

    public JobPositionService(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    public List getAll() {
        return jobPositionRepository.findAll();
    }
}
