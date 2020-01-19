package analytics.repository;

import analytics.entity.Employee;
import analytics.entity.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobPositionRepository extends JpaRepository<JobPosition, Long> {

    @Query(value = "SELECT J.* FROM JOB_POSITION J, EMPLOYEE E WHERE E.ID = ?1 AND J.ID = E.JOB_POSITION",
    nativeQuery = true)
    JobPosition findJobPositionByEmployee(long id);
}
