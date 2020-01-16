package analytics.controller;

import analytics.config.MyUserPrincipal;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

@Controller
public class DiagramController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
        int numberDay = new GregorianCalendar().get(Calendar.DAY_OF_WEEK);
        ArrayList<Double> min = new ArrayList<>();
        for (int i = 2; i <= numberDay; i++) {
            try {
                min.add(Double.parseDouble(workLogService.getStartDayByDay(
                        ((MyUserPrincipal) authentication.getPrincipal())
                                .getEmployee(), i).replace(':', '.').substring(0, 5)));

            } catch (Exception ignored) {
                min.add(0d);
            }
        }

        ArrayList<Double> max = new ArrayList<>();
        for (int i = 2; i <= numberDay; i++) {
            try {
                max.add(Double.parseDouble(workLogService.getEndDayByDay(
                        ((MyUserPrincipal) authentication.getPrincipal())
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
