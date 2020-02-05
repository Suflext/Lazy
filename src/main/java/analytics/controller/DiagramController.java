package analytics.controller;

import analytics.General;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DiagramController extends General {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
//        List<WorkLog> workLogs = workLogService.getListStartWorkWeekByEmployeeId(getEmployee(authentication).getId());
//        model.addAttribute("AtWork", workLogs);
//        model.addAttribute("LeftWork", max);
        return "diagram";
    }
}
