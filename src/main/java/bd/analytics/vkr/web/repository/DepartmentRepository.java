package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findById(long id);
}
