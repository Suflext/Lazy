package analytics.repository;

import analytics.entity.JobPosition;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface JobPositionRepository extends PagingAndSortingRepository<JobPosition, Long> {

    List<JobPosition> findAll();
}
