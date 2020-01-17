package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LatecomersController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/latecomers")
    public String rating(Model model) {
        model.addAttribute("list", employeeService.getLatecomers());
        return "latecomers";
    }
}
