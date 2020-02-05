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
    public String employeeWhoWork(Model model) {
        model.addAttribute("employeeWhoWork", workLogService.getEmployeeWhoWork());
        return "active";
    }
}
