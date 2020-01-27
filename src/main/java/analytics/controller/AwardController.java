package analytics.controller;

import analytics.config.MyUserPrincipal;
import analytics.entity.JobPosition;
import analytics.service.JobPositionService;
import analytics.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AwardController {

    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    @GetMapping(value = "/award")
    public String award(Model model, Authentication authentication) {
        JobPosition jobPosition = jobPositionService.getJobPositionByEmployee(((MyUserPrincipal)authentication.getPrincipal()).getEmployee());
        long salary = jobPosition.getSalary();
        float percent = (float)systemPropertiesService.getPercent("bonus") / 100;
        model.addAttribute("award", (long)(salary *  percent));
        return "award";
    }
}
