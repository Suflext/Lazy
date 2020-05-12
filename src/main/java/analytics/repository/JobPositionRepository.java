package analytics.repository;

import analytics.entity.JobPosition;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobPositionRepository extends PagingAndSortingRepository<JobPosition, Long> {

    List<JobPosition> findAll();
}
