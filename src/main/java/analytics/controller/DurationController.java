package analytics.controller;

import analytics.config.MyUserPrincipal;
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
public class DurationController {

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping("/duration")
    public String duration(Model model, Authentication authentication) {
        long dayDuration = workLogService.getDuration(getEmployee(authentication), LocalDate.now());
        model.addAttribute("DayWorkedAlready", workLogService.getStringFormatDuration(dayDuration));
        long timeWork = getEmployee(authentication).getJobPosition().getWeekHours();
        model.addAttribute("DayIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 5));//5-daysWork
        model.addAttribute("DayLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 5 - dayDuration));//5-daysWork

        long weekDuration = workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY), getEmployee(authentication));
        model.addAttribute("WeekWorkedAlready", workLogService.getStringFormatDuration(
                weekDuration));
        model.addAttribute("WeekIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600));
        model.addAttribute("WeekLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 - weekDuration));

        model.addAttribute("MonthWorkedAlready", workLogService.getStringFormatDuration(
                workLogReportService.timeWorkUp("month", LocalDate.now().withDayOfMonth(1), getEmployee(authentication))));
        return "duration";
    }

    private Employee getEmployee(Authentication authentication) {
        return ((MyUserPrincipal) authentication.getPrincipal()).getEmployee();
    }
}
