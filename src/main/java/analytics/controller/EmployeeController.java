package analytics.controller;

import analytics.config.MyUserPrincipal;
import analytics.entity.Employee;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping(value = {"/", "/user"})
    public String user(Model model, Authentication authentication) {
        model.addAttribute("name", getEmployee(authentication).getFirstName());
        model.addAttribute("employee", getEmployee(authentication));
        model.addAttribute("user", getEmployee(authentication).getLogin());
        model.addAttribute("role", getEmployee(authentication).getRole());
        return "user";
    }

    @GetMapping("/all")
    public String employee(Model model) {
        model.addAttribute("worklogs", workLogService.findAll());
        model.addAttribute("work_log_report", workLogReportService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("positions", positionService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "all";
    }

    private Employee getEmployee(Authentication authentication) {
        return ((MyUserPrincipal) authentication.getPrincipal()).getEmployee();
    }
}