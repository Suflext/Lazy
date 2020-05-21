package analytics.service;

import analytics.repository.SystemPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesService {

    private final SystemPropertyRepository systemPropertyRepository;

    public SystemPropertiesService(SystemPropertyRepository systemPropertyRepository) {
        this.systemPropertyRepository = systemPropertyRepository;
    }

    public long getValue(String key) {
        return Long.parseLong(systemPropertyRepository.findValueByKey(key));
    }
}
