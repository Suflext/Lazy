package analytics.controller;

import analytics.Client;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;

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

    @GetMapping("/beforeLogOut")
    public String logout(Authentication authentication) {
        Client myUserDetails = getMyUserDetails(authentication);
        workLogService.addEndDate(myUserDetails.getEmployee());
        return "redirect:/logout";
    }

    @GetMapping("/afterLogIn")
    public String logIn(Authentication authentication) {
        Client myUserDetails = getMyUserDetails(authentication);
        workLogService.addStartDate(myUserDetails.getEmployee());
        return "redirect:/user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping(value = {"/", "/user"})
    public String user(Model model, Authentication authentication) {
        Client user = getMyUserDetails(authentication);
        model.addAttribute("name", user.getEmployee().getFirstName());
        model.addAttribute("employee", user.getEmployee());
        model.addAttribute("user", user.getUsername());
        model.addAttribute("role", user.getAuthorities());
        return "user";
    }

    @GetMapping("/duration")
    public String duration(Model model, Authentication authentication) {
        Client myUserDetails = getMyUserDetails(authentication);
        long dayDuration = workLogService.getDuration(myUserDetails.getEmployee(), LocalDate.now());
        model.addAttribute("DayWorkedAlready", workLogService.getStringFormatDuration(dayDuration));
        long timeWork = myUserDetails.getEmployee().getJobPosition().getWeekHours();
        model.addAttribute("DayIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 6));//6-daysWork
        model.addAttribute("DayLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 6 - dayDuration));//6-daysWork

        long weekDuration = workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY), myUserDetails.getEmployee());
        model.addAttribute("WeekWorkedAlready", workLogService.getStringFormatDuration(
                weekDuration));
        model.addAttribute("WeekIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600));
        model.addAttribute("WeekLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 - weekDuration));

        model.addAttribute("MonthWorkedAlready", workLogService.getStringFormatDuration(
                workLogReportService.timeWorkUp("month", LocalDate.now().withDayOfMonth(1), myUserDetails.getEmployee())));
        return "duration";
    }

    @GetMapping("/allowance")
    public String salaryAllowance(Model model, Authentication authentication) {
        Client myUserDetails = getMyUserDetails(authentication);
        long allowance = myUserDetails.getEmployee().getJobPosition().getWeekHours() * 3600 -
                workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY),
                        myUserDetails.getEmployee());
        model.addAttribute("SalaryAllowance", (allowance < 0) ? "Вы переработали : " +
                workLogService.getStringFormatDuration(-allowance) : "Вы не доработали : " +
                workLogService.getStringFormatDuration(allowance));
        return "allowance";
    }

    @GetMapping("/active")
    public String activeEmployee(Model model) {
        model.addAttribute("activeEmployees", workLogService.getActiveEmployee());
        return "active";
    }

    @GetMapping("/rating")
    public String rating(Model model) {
        model.addAttribute("ratings", employeeService.getRatingEmployee());
        return "rating";
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

    private Client getMyUserDetails(Authentication authentication) {
        Client myUserDetails = new Client(
                ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getUsername(),
                ((org.springframework.security.core.userdetails.User) authentication.getPrincipal()).getAuthorities()
        );
        myUserDetails.setEmployee(employeeService.findByLogin(myUserDetails.getUsername()));
        return myUserDetails;
    }
}