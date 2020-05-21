package analytics.service;

import analytics.entity.Department;
import analytics.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepo;

    public List<Department> getAll() {
        return departmentRepo.findAll();
    }

}
