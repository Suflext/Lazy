package analytics.repository;

import analytics.entity.SystemProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SystemPropertyRepository extends JpaRepository<SystemProperties, Long> {

    @Query(value = "SELECT P.VALUE FROM SYSTEM_PROPERTY P WHERE P.KEY = ?1",
    nativeQuery = true)
    String findValueByKey(String percent);
}
