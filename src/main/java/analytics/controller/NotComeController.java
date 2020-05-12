package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotComeController extends BasicController{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/notComeToday")
    public String notComeToday(Model model, Authentication authentication) {
        model.addAttribute("notComeToday", employeeService.getNotComeToday());
        model.addAttribute("user", getEmployee(authentication));
        return "notComeToday";
    }
}
