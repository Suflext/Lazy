package analytics.service;

import analytics.repository.SystemPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesService {

    private SystemPropertyRepository systemPropertyRepository;

    public SystemPropertiesService(SystemPropertyRepository systemPropertyRepository) {
        this.systemPropertyRepository = systemPropertyRepository;
    }

    public long getKey(String key) {
        return Long.parseLong(systemPropertyRepository.findValueByKey(key));
    }
}
