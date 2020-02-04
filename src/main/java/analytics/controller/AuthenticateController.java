package analytics.controller;

import analytics.config.EmployeePrincipal;
import analytics.entity.Employee;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticateController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/afterLogIn")
    public String logIn(Authentication authentication) {
        workLogService.addStartDate(getEmployee(authentication));
        return "redirect:/user";
    }

    private Employee getEmployee(Authentication authentication) {
        return ((EmployeePrincipal) authentication.getPrincipal()).getEmployee();
    }

    @GetMapping("/beforeLogOut")
    public String logout(Authentication authentication) {
        workLogService.addEndDate(getEmployee(authentication));
        return "redirect:/logout";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
