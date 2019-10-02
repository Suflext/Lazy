package bd.analytics.vkr.web.service;

import bd.analytics.vkr.web.repository.PositionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PositionService {

    private PositionRepository positionRepo;

    public PositionService(PositionRepository positionRepo) {
        this.positionRepo = positionRepo;
    }

    public List findAll() {
        return new ArrayList<>(positionRepo.findAll());
    }

}
