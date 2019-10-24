package analytics.controller;

import analytics.MyUserDetails;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.time.LocalTime;

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

    @Autowired
    private JobPositionService jobPositionService;

    @PostMapping("/log")
    public String logout(Authentication authentication) {
        MyUserDetails myUserDetails = getMyUserDetails(authentication);
        workLogService.addEndDate(LocalTime.now(), myUserDetails.getEmployee());
        return "redirect:/logout";
    }

    @GetMapping("/log")
    public String logIn(Authentication authentication) {
        MyUserDetails myUserDetails = getMyUserDetails(authentication);
        workLogService.addStartDate(LocalTime.now(), myUserDetails.getEmployee());
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/user"})
    public String user(Model model, Authentication authentication) {
        MyUserDetails myUserDetails = getMyUserDetails(authentication);
        model.addAttribute("name", myUserDetails.getEmployee().getFirstName());
        model.addAttribute("user", myUserDetails.getUsername());
        model.addAttribute("role", myUserDetails.getAuthorities());
        return "user";
    }

    @GetMapping("/duration")
    public String duration(Model model, Authentication authentication) {
        MyUserDetails myUserDetails = getMyUserDetails(authentication);
        model.addAttribute("user", myUserDetails.getUsername());
        long duration =workLogService.getDuration(myUserDetails.getEmployee(), LocalDate.now());
        model.addAttribute("WorkedAlready", workLogService.getStringFormatDuration(duration));
        model.addAttribute("IdeaWork", workLogService.getStringFormatDuration(
                myUserDetails.getEmployee().getJobPosition().getWeekHours()*3600/6));//6-daysWork
        model.addAttribute("LeftWork", workLogService.getStringFormatDuration(
                myUserDetails.getEmployee().getJobPosition().getWeekHours()*3600/6 - duration));//6-daysWork
        return "duration";
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

    private MyUserDetails getMyUserDetails(Authentication authentication) {
        MyUserDetails myUserDetails = new MyUserDetails(
                ((User)authentication.getPrincipal()).getUsername(),
                ((User)authentication.getPrincipal()).getAuthorities()
        );
        myUserDetails.setEmployee(employeeService.findByLogin(myUserDetails.getUsername()));
        return myUserDetails;
    }
}