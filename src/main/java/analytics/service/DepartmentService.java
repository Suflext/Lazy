package analytics.service;

import analytics.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private DepartmentRepository departmentRepo;

    public DepartmentService(DepartmentRepository departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public List getAll() {
        return departmentRepo.findAll();
    }

}
