package analytics.controller;

import analytics.General;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DiagramController extends General {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
        List<Double> list = getListStartAndFinishTimeWork(workLogService
                .getListStartAndFinishWorkWeekByEmployeeId(getEmployee(authentication).getId()));
        model.addAttribute("startWork", list.subList(0, list.size() / 2));
        model.addAttribute("finishWork", list.subList(list.size() / 2, list.size()));
        return "diagram";
    }
}
