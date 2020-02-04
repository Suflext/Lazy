package analytics.controller;

import analytics.config.EmployeePrincipal;
import analytics.entity.Employee;
import analytics.service.WorkLogReportService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class AllowanceController {

    @Autowired
    private WorkLogReportService workLogReportService;

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/allowance")
    public String allowance(Model model, Authentication authentication) {
        long allowance = getEmployee(authentication).getJobPosition().getWeekHours() * 3600 -
                workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY),
                        getEmployee(authentication));
        model.addAttribute("SalaryAllowance", (allowance < 0)
                ? "Вы переработали : " + workLogService.getStringFormatDuration(-allowance)
                : "Вы не доработали : " + workLogService.getStringFormatDuration(allowance));
        return "allowance";
    }

    private Employee getEmployee(Authentication authentication) {
        return ((EmployeePrincipal) authentication.getPrincipal()).getEmployee();
    }
}
