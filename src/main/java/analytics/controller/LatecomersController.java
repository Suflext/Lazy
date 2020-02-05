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
    public String latecomers(Model model) {
        model.addAttribute("latecomers", employeeService.getLatecomers());
        return "latecomers";
    }
}
