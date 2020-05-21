package analytics.service;

import analytics.repository.SystemPropertyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SystemPropertiesService {

    private final SystemPropertyRepository systemPropertyRepository;

    public long getValue(String key) {
        return Long.parseLong(systemPropertyRepository.findValueByKey(key));
    }
}
