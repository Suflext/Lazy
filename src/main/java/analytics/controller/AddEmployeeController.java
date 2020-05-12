package analytics.controller;

import analytics.entity.Employee;
import analytics.service.DepartmentService;
import analytics.service.EmployeeService;
import analytics.service.JobPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AddEmployeeController extends BasicController{

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private EmployeeService employeeService;


    @GetMapping("/addEmployee")
    public String addEmployee(Model model, Authentication authentication){
        model.addAttribute("listPositions", jobPositionService.getAll());
        model.addAttribute("listDepartments", departmentService.getAll());
        model.addAttribute("user", getEmployee(authentication));
        return "addEmployee";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("newEmployee") Employee employee){
        employeeService.add(employee);
        return "redirect:/user";
    }
}
