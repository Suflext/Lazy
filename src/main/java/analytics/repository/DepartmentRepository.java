package analytics.repository;

import analytics.entity.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

    List<Department> findAll();
}
