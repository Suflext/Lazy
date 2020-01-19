package analytics.controller;

import analytics.config.MyUserPrincipal;
import analytics.entity.Employee;
import analytics.entity.JobPosition;
import analytics.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Calendar;

@Controller
public class EmployeeController {

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

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    @GetMapping(value = {"/", "/user"})
    public String user() {
        return "user";
    }

    @GetMapping(value = "/award")
    public String award(Model model, Authentication authentication) {
        LocalDate date = LocalDate.now();
        JobPosition jobPosition = jobPositionService.getJobPositionByEmployee(((MyUserPrincipal)authentication.getPrincipal()).getEmployee());
        long salary = jobPosition.getSalary();
        long countEarly = workLogService.getCountEarlyComers(date.withDayOfMonth(1), date, ((MyUserPrincipal)authentication.getPrincipal()).getEmployee());
        int actualMaximum = Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
        int countWeekends = getCountWeekends(date.withDayOfMonth(1), actualMaximum);
        int workDay =  actualMaximum - countWeekends;
        long countLate = workDay - countEarly;
        float percent = (float)systemPropertiesService.getPercent("bonus") / 100;
        model.addAttribute("award", (long)(salary *  percent * (workDay - countLate) / workDay));
        return "award";
    }

    private int getCountWeekends(LocalDate date, int max){
        int c = 0;
        for (int i = 1; i <= max; i++) {
            if (date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || date.getDayOfWeek().equals(DayOfWeek.SATURDAY)){
                c++;
            }
            date = date.plusDays(1);
        }
        return c;
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

    private Employee getEmployee(Authentication authentication) {
        return ((MyUserPrincipal) authentication.getPrincipal()).getEmployee();
    }
}