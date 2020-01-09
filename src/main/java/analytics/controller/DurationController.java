package analytics.controller;

import analytics.config.MyUserPrincipal;
import analytics.service.WorkLogReportService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public String duration(Model model) {
        long dayDuration = workLogService.getDuration(MyUserPrincipal.getEmployee(), LocalDate.now());
        model.addAttribute("DayWorkedAlready", workLogService.getStringFormatDuration(dayDuration));
        long timeWork = MyUserPrincipal.getEmployee().getJobPosition().getWeekHours();
        model.addAttribute("DayIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 6));//6-daysWork
        model.addAttribute("DayLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 / 6 - dayDuration));//6-daysWork

        long weekDuration = workLogReportService.timeWorkUp("week", LocalDate.now().with(DayOfWeek.MONDAY), MyUserPrincipal.getEmployee());
        model.addAttribute("WeekWorkedAlready", workLogService.getStringFormatDuration(
                weekDuration));
        model.addAttribute("WeekIdeaWork", workLogService.getStringFormatDuration(
                timeWork * 3600));
        model.addAttribute("WeekLeftWork", workLogService.getStringFormatDuration(
                timeWork * 3600 - weekDuration));

        model.addAttribute("MonthWorkedAlready", workLogService.getStringFormatDuration(
                workLogReportService.timeWorkUp("month", LocalDate.now().withDayOfMonth(1), MyUserPrincipal.getEmployee())));
        return "duration";
    }
}
