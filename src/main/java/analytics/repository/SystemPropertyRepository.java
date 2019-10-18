package analytics.repository;

import analytics.entity.SystemProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPropertyRepository extends JpaRepository<SystemProperties, Long> {
}
