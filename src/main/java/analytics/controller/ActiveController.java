package analytics.controller;

import analytics.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ActiveController {

    @Autowired
    private WorkLogService workLogService;

    @GetMapping("/active")
    public String activeEmployee(Model model) {
        model.addAttribute("activeEmployees", workLogService.getActiveEmployee());
        return "active";
    }
}
