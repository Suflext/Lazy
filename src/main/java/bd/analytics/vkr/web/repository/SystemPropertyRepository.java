package bd.analytics.vkr.web.repository;

import bd.analytics.vkr.web.entity.SystemProperties;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SystemPropertyRepository extends JpaRepository<SystemProperties, Long> {
}
