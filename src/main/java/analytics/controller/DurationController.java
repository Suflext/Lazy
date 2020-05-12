package analytics.controller;

import analytics.entity.Employee;
import analytics.service.WorkLogReportService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;

import static java.time.DayOfWeek.MONDAY;
import static java.time.DayOfWeek.SATURDAY;
import static java.time.LocalDate.now;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.getInstance;

@Controller
public class DurationController extends BasicController {

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private WorkLogReportService workLogReportService;

    @GetMapping("/duration")
    public String duration(Model model, Authentication authentication) {
        LocalDate now = now();
        Employee employee = getEmployee(authentication);
        Long dayDuration = workLogService.getTimeWorkByDayAndEmployeeId(now, employee.getId());
        model.addAttribute("DayWorkedAlready", getStringFormatDuration(dayDuration));

        Long weekHours = employee.getJobPosition().getWeekHours();
        model.addAttribute("DayIdeaWork", getStringFormatDuration(
                weekHours * 3600 / 5));

        model.addAttribute("DayLeftWork", getStringFormatDuration(
                weekHours * 3600 / 5
                        - dayDuration));

        Long weekDuration = workLogReportService.getAllTimeWorkBetweenTwoDatesByEmployeeId(
                now.with(MONDAY),
                now.with(SATURDAY),
                employee.getId());

        model.addAttribute("WeekWorkedAlready", getStringFormatDuration(
                weekDuration));
        model.addAttribute("WeekIdeaWork", getStringFormatDuration(
                weekHours * 3600));
        model.addAttribute("WeekLeftWork", getStringFormatDuration(
                weekHours * 3600 - weekDuration));

        model.addAttribute("MonthWorkedAlready", getStringFormatDuration(
                workLogReportService.getAllTimeWorkBetweenTwoDatesByEmployeeId(
                        now.withDayOfMonth(1),
                        now.withDayOfMonth(getInstance().getActualMaximum(DAY_OF_MONTH)),
                        employee.getId())));
        model.addAttribute("user", getEmployee(authentication));
        return "duration";
    }
}
