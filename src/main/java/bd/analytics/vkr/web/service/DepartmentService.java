package bd.analytics.vkr.web.service;

import bd.analytics.vkr.web.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public List findAll() {
        return new ArrayList<>(departmentRepo.findAll());
    }

}
