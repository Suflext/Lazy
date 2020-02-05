package analytics.controller;

import analytics.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RatingController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/rating")
    public String rating(Model model) {
        model.addAttribute("ratings", employeeService.getRatingEmployee());
        return "rating";
    }
}

