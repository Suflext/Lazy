package analytics.service;

import analytics.entity.Employee;
import analytics.entity.JobPosition;
import analytics.repository.JobPositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobPositionService {

    private JobPositionRepository jobPositionRepository;

    public JobPositionService(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    public List findAll() {
        return new ArrayList<>(jobPositionRepository.findAll());
    }

    public JobPosition getJobPositionByEmployee(Employee employee) {
        return jobPositionRepository.findJobPositionByEmployee(employee.getId());
    }
}
