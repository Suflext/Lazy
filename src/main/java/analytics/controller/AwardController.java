package analytics.controller;

import analytics.service.SystemPropertiesService;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwardController extends BasicController {

    @Autowired
    private SystemPropertiesService systemService;

    @Autowired
    private WorkLogService workLogService;

    @GetMapping(value = "/award")
    public String award(Model model, Authentication authentication) {
        model.addAttribute("table",
                workLogService.getListAwards(getEmployee(authentication),
                        systemService.getValue("bonus") / 100f,
                        systemService.getValue("penalty") / 100f));
        model.addAttribute("user", getEmployee(authentication));
        return "award";
    }
}
