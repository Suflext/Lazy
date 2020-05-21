package analytics.controller;

import analytics.service.EmployeeService;
import analytics.service.SystemPropertiesService;
import analytics.service.WorkLogService;
import analytics.service.model.viewSumAward;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class SumAwardController extends BasicController {

    @Autowired
    private SystemPropertiesService systemService;

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/sumAward")
    public String sumAward(Model model, Authentication authentication) {
        if (getEmployee(authentication).getRole().equals("ADMIN")) {
            List<viewSumAward> sumAwardsList = workLogService.getSumAward(
                    systemService.getValue("bonus") / 100f,
                    systemService.getValue("penalty") / 100f);
            long sumAward = sumAwardsList.stream().mapToLong(viewSumAward::getAward).sum();

            model.addAttribute("sumAwardList", sumAwardsList);
            model.addAttribute("sumAward", sumAward);
            model.addAttribute("user", getEmployee(authentication));
            return "sumAward";
        } else {
            return "redirect:/index";
        }
    }

    @GetMapping("/request")
    public String request(@RequestParam long id, Model model, Authentication authentication){
        System.out.println(id);
        model.addAttribute("table",
                workLogService.getListAwards(employeeService.findById(id),
                        systemService.getValue("bonus") / 100f,
                        systemService.getValue("penalty") / 100f));
        model.addAttribute("user", getEmployee(authentication));
        return "award";
    }
}
