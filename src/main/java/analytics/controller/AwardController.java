package analytics.controller;

import analytics.entity.Employee;
import analytics.entity.JobPosition;
import analytics.service.SystemPropertiesService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.time.LocalDate.now;

@Controller
public class AwardController extends BasicController {

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    @Autowired
    private WorkLogService workLogService;

    @GetMapping(value = "/award")
    public String award(Model model, Authentication authentication) {
        LocalDate start = now().withDayOfMonth(1);
        LocalDate end = now();
        Employee employee = getEmployee(authentication);
        JobPosition jobPosition = employee.getJobPosition();
        long salary = jobPosition.getSalary();
        long countDays = Period.between(now().withDayOfMonth(1), now()).getDays() + 1;
        float bonus = systemPropertiesService.getValue("bonus") / 100f;
        float penalty = systemPropertiesService.getValue("penalty") / 100f;
        long newSalary = 0;

        if (jobPosition.getStartTime() instanceof LocalTime) {
            long lateness = workLogService.getLatecomerById(employee);
            newSalary = (long) (salary - salary * penalty * lateness);
            model.addAttribute("lateness", lateness);
        }

        Map<LocalDate, Long> timeWorked = workLogService.getListBetweenTwoDateByEmployee(employee, start, end);
        long absence = calculateAbsence(countDays, timeWorked);
        AtomicInteger overwork = new AtomicInteger();
        calculateOverwork(timeWorked, overwork);
        newSalary = (long) (salary
                - salary * penalty * absence * 2
                + salary * bonus * ((salary / 160) * overwork.get()));

        model.addAttribute("overwork", overwork.get());
        model.addAttribute("absence", absence);
        model.addAttribute("award", newSalary);
        model.addAttribute("user", employee);
        return "award";
    }

    private long calculateAbsence(long countDays, Map<LocalDate, Long> timeWorked) {
        long workedDays = countDays - countWeekends(countDays);
        AtomicInteger count = new AtomicInteger();

        timeWorked.forEach((localDate, aLong) -> {
            if (!(localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                count.getAndIncrement();
            }
        });

        return workedDays - count.get();
    }

    private int countWeekends(long countDays) {
        int count = 0;
        LocalDate start = now().withDayOfMonth(1);
        for (int i = 1; i < countDays; i++) {
            if (start.getDayOfWeek().equals(DayOfWeek.SATURDAY) || start.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                count++;
            }
            start = start.plusDays(1);
        }
        return count;
    }

    private void calculateOverwork(Map<LocalDate, Long> timeWorked, AtomicInteger overwork) {
        timeWorked.forEach((localDate, aLong) -> {
            int workedHours = (int) (aLong / 3600);
            if (localDate.getDayOfWeek().equals(DayOfWeek.SATURDAY) || localDate.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                overwork.addAndGet(workedHours);
            } else if (workedHours > 8) {
                overwork.addAndGet(workedHours - 8);
            }
        });
    }
}
