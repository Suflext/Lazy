package analytics.controller;

import analytics.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RatingController {

    private final EmployeeService employeeService;

    @GetMapping("/rating")
    public String rating(Model model) {
        model.addAttribute("ratings", employeeService.getRatingEmployee());
        return "rating";
    }
}

