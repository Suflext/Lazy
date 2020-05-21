package analytics.controller;

import analytics.service.DepartmentService;
import analytics.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class DepartmentsController extends BasicController {

    private final DepartmentService departmentService;
    private final EmployeeService employeeService;

    @GetMapping("/departments")
    public String departments(Model model, Authentication authentication) {
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("user", getEmployee(authentication));
        return "departments";
    }

    @GetMapping("/departEmployee")
    public String departEmployee(@RequestParam long id, Model model, Authentication authentication){
        model.addAttribute("employees", employeeService.getAllByDepartmentId(id));
        model.addAttribute("user", getEmployee(authentication));
        return "listEmployee";
    }
}
