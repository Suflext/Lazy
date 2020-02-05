package analytics.repository;

import analytics.entity.DepartmentBonus;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentBonusRepository extends PagingAndSortingRepository<DepartmentBonus, Long> {
}
