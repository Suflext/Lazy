package analytics.controller;

import analytics.service.WorkLogReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class WeeklyReportController extends BasicController {

    private final WorkLogReportService service;

    @GetMapping("/weeklyReports")
    public String weeklyReport(Model model, Authentication authentication) {
        model.addAttribute("weeklyReports", service.getAllByStartDate());
        model.addAttribute("user", getEmployee(authentication));
        return "weeklyReports";
    }
}
