package analytics.controller;

import analytics.service.WorkLogReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Period;

import static java.time.DayOfWeek.*;
import static java.time.LocalDate.now;

@Controller
public class AllowanceController extends BasicController {

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping("/allowance")
    public String allowance(Model model, Authentication authentication) {
        Long weekHours = getEmployee(authentication).getJobPosition().getWeekHours();
        long allowance = weekHours * 3600 -
                workLogReportService.getAllTimeWorkBetweenTwoDatesByEmployeeId(
                        now().with(MONDAY),
                        now(),
                        getEmployee(authentication));

        LocalDate date = now().with(FRIDAY);
        if (now().getDayOfWeek().equals(SATURDAY)) date = now().with(SATURDAY);
        else if (now().getDayOfWeek().equals(SUNDAY)) date = now().with(SUNDAY);

        allowance /= Period.between(now(), date).getDays() + 1;

        model.addAttribute("SalaryAllowance", (allowance < 0)
                ? "Вы переработали : " + getStringFormatDuration(-allowance)
                : "Вы не доработали : " + getStringFormatDuration(allowance));
        model.addAttribute("user", getEmployee(authentication));
        return "allowance";
    }
}
