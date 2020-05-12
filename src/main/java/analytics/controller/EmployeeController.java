package analytics.controller;

import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController extends BasicController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping(value = {"/", "/user"})
    public String user(Authentication authentication, Model model) {
        model.addAttribute("user", getEmployee(authentication));
        return "user";
    }

    @GetMapping("/all")
    public String employee(Model model, Authentication authentication) {
        model.addAttribute("worklogs", workLogService.getAll());
        model.addAttribute("work_log_report", workLogReportService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("positions", jobPositionService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        model.addAttribute("user", getEmployee(authentication));
        return "all";
    }
}