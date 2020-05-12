package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LatecomersController extends BasicController{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/latecomers")
    public String latecomers(Model model, Authentication authentication) {
        model.addAttribute("latecomers", employeeService.getLatecomers());
        model.addAttribute("user", getEmployee(authentication));
        return "latecomers";
    }
}
