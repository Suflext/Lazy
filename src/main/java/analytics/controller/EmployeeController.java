package analytics.controller;

import analytics.General;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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
    public String user(Model model, Authentication authentication) {
        model.addAttribute("test", workLogService.getListStartWorkWeekByEmployeeId(getEmployee(authentication).getId()));
        model.addAttribute("test2", workLogService.getListFinishWorkWeekByEmployeeId(getEmployee(authentication).getId()));
        return "user";
    }

    @GetMapping("/all")
    public String employee(Model model) {
        model.addAttribute("worklogs", workLogService.findAll());
        model.addAttribute("work_log_report", workLogReportService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("positions", jobPositionService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "all";
    }
}