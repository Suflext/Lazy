package analytics.controller;

import analytics.General;
import analytics.service.WorkLogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Controller
public class AllowanceController extends General {

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping("/allowance")
    public String allowance(Model model, Authentication authentication) {
        long allowance = getEmployee(authentication).getJobPosition().getWeekHours() * 3600 -
                workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY),
                        getEmployee(authentication));
        model.addAttribute("SalaryAllowance", (allowance < 0)
                ? "Вы переработали : " + getStringFormatDuration(-allowance)
                : "Вы не доработали : " + getStringFormatDuration(allowance));
        return "allowance";
    }
}
