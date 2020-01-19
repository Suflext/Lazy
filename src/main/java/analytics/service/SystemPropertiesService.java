package analytics.service;

import analytics.repository.SystemPropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertiesService {

    private SystemPropertyRepository systemPropertyRepository;

    public SystemPropertiesService(SystemPropertyRepository systemPropertyRepository) {
        this.systemPropertyRepository = systemPropertyRepository;
    }


    public long getPercent(String percent) {
        return Long.parseLong(systemPropertyRepository.findValueByKey(percent));
    }
}
