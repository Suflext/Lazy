package analytics.controller;

import analytics.General;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController extends General {

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
    public String user() {
        return "user";
    }

    @GetMapping("/all")
    public String employee(Model model) {
        model.addAttribute("worklogs", workLogService.getAll());
        model.addAttribute("work_log_report", workLogReportService.getAll());
        model.addAttribute("employees", employeeService.getAll());
        model.addAttribute("positions", jobPositionService.getAll());
        model.addAttribute("departments", departmentService.getAll());
        return "all";
    }
}