package analytics.controller;

import analytics.MyUserDetails;
import analytics.entity.Employee;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

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

    private MyUserDetails myUserDetails;

    private Authentication authentication;

    @PostMapping("/log")
    public String logout(@ModelAttribute Employee employee) {
        workLogService.addEndDate(new Date(), myUserDetails.getEmployee());
        return "redirect:/logout";
    }

    @GetMapping("/logns")
    public String logIn() {
        authentication = SecurityContextHolder.getContext().getAuthentication();
//        myUserDetails = new MyUserDetails((UserDetails) authentication.getPrincipal());
        myUserDetails = new MyUserDetails(
                ((User)authentication.getPrincipal()).getUsername(),
                ((User)authentication.getPrincipal()).getAuthorities()
        );
        myUserDetails.setEmployee(employeeService.findByLogin(myUserDetails.getUsername()));
        workLogService.addStartDate(new Date(), myUserDetails.getEmployee());
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/user"})
    public String user(Model model) {
        model.addAttribute("name", myUserDetails.getEmployee().getFirstName());
        model.addAttribute("user", myUserDetails.getUsername());
        model.addAttribute("role", authentication.getAuthorities());
        model.addAttribute("employee", myUserDetails.getEmployee());
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
}