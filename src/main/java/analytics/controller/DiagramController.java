package analytics.controller;

import analytics.config.EmployeePrincipal;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;

@Controller
public class DiagramController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
        int numberDay = Calendar.DAY_OF_WEEK;
        ArrayList<Double> min = new ArrayList<>();
        for (int i = 0; i < numberDay; i++) { //
            if (i == 5) break;
            try {
                min.add(Double.parseDouble(workLogService.getStartDayByDay(
                        ((EmployeePrincipal) authentication.getPrincipal())
                                .getEmployee(), i).replace(':', '.').substring(0, 5)));

            } catch (Exception ignored) {
                min.add(0d);
            }
        }

        ArrayList<Double> max = new ArrayList<>();
        for (int i = 0; i < numberDay; i++) {
            if (i == 5) break;
            try {
                max.add(Double.parseDouble(workLogService.getEndDayByDay(
                        ((EmployeePrincipal) authentication.getPrincipal())
                                .getEmployee(), i).replace(':', '.').substring(0, 5)));

            } catch (Exception ignored) {
                max.add(0d);
            }
        }

        model.addAttribute("AtWork", min);
        model.addAttribute("LeftWork", max);
        return "diagram";
    }
}
