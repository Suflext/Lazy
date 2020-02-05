package analytics.controller;

import analytics.General;
import analytics.service.WorkLogReportService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;

import static java.time.LocalDate.now;

@Controller
public class DurationController extends General {

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping("/duration")
    public String duration(Model model, Authentication authentication) {
        long dayDuration = workLogService.getDuration(getEmployee(authentication), now());
        model.addAttribute("DayWorkedAlready", getStringFormatDuration(dayDuration));
        long timeWork = getEmployee(authentication).getJobPosition().getWeekHours();
        model.addAttribute("DayIdeaWork", getStringFormatDuration(
                timeWork * 3600 / 5));//5-daysWork
        model.addAttribute("DayLeftWork", getStringFormatDuration(
                timeWork * 3600 / 5 - dayDuration));//5-daysWork

        long weekDuration = workLogReportService.timeWorkUp("week", now().with(DayOfWeek.MONDAY), getEmployee(authentication));
        model.addAttribute("WeekWorkedAlready", getStringFormatDuration(
                weekDuration));
        model.addAttribute("WeekIdeaWork", getStringFormatDuration(
                timeWork * 3600));
        model.addAttribute("WeekLeftWork", getStringFormatDuration(
                timeWork * 3600 - weekDuration));

        model.addAttribute("MonthWorkedAlready", getStringFormatDuration(
                workLogReportService.timeWorkUp("month", now().withDayOfMonth(1), getEmployee(authentication))));
        return "duration";
    }
}
