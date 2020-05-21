package analytics.service;

import analytics.entity.JobPosition;
import analytics.repository.JobPositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPositionService {

    private final JobPositionRepository jobPositionRepository;

    public List<JobPosition> getAll() {
        return jobPositionRepository.findAll();
    }
}
