package analytics.service;

import analytics.repository.JobPositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    private JobPositionRepository positionRepo;

    public PositionService(JobPositionRepository positionRepo) {
        this.positionRepo = positionRepo;
    }

    public List findAll() {
        return new ArrayList<>(positionRepo.findAll());
    }

}
