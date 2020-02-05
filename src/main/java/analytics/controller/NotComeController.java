package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NotComeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/notComeToday")
    public String notComeToday(Model model) {
        model.addAttribute("notComeToday", employeeService.getNotComeToday());
        return "notComeToday";
    }
}
