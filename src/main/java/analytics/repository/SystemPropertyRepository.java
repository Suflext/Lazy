package analytics.repository;

import analytics.entity.SystemProperties;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SystemPropertyRepository extends PagingAndSortingRepository<SystemProperties, Long> {

    @Query(value = "SELECT P.VALUE FROM SYSTEM_PROPERTY P WHERE P.KEY = ?1",
    nativeQuery = true)
    String findValueByKey(String key);
}
