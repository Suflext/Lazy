package analytics.controller;

import analytics.config.MyUserPrincipal;
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
    public String salaryAllowance(Model model, Authentication authentication) {
        long allowance = MyUserPrincipal.getEmployee().getJobPosition().getWeekHours() * 3600 -
                workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY),
                        MyUserPrincipal.getEmployee());
        model.addAttribute("SalaryAllowance", (allowance < 0) ? "Вы переработали : " +
                workLogService.getStringFormatDuration(-allowance) : "Вы не доработали : " +
                workLogService.getStringFormatDuration(allowance));
        return "allowance";
    }
}
