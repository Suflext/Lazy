package analytics.controller;

import analytics.config.EmployeeDetailService;
import analytics.config.MyUserPrincipal;
import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticateController {

    @Autowired
    private WorkLogService workLogService;

    @Autowired
    private EmployeeDetailService employeeDetailService;

    public MyUserPrincipal user;

    @GetMapping("/afterLogIn")
    public String logIn(Authentication authentication) {
        user = (MyUserPrincipal) employeeDetailService.loadUserByUsername(authentication.getName());
        workLogService.addStartDate(MyUserPrincipal.getEmployee());
        return "redirect:/user";
    }

    @GetMapping("/beforeLogOut")
    public String logout() {
        workLogService.addEndDate(MyUserPrincipal.getEmployee());
        return "redirect:/logout";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

}
