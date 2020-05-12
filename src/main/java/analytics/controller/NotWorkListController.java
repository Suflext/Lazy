package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotWorkListController extends BasicController{

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/notWorkList")
    public String notWorkList(Model model, Authentication authentication) {
        model.addAttribute("notWorkList", employeeService.getNotWorkList());
        model.addAttribute("user", getEmployee(authentication));
        return "notWorkList";
    }
}

