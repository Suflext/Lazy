package analytics.controller;

import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActiveController extends BasicController{

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/active")
    public String employeeWhoWork(Model model, Authentication authentication) {
        if (getEmployee(authentication).getRole().equals("ADMIN")) {
            model.addAttribute("employeeWhoWork", workLogService.getEmployeeWhoWork());
            model.addAttribute("user", getEmployee(authentication));
            return "active";
        } else{
            return "redirect:/index";
        }
    }
}
