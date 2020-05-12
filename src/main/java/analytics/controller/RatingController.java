package analytics.controller;

import analytics.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class RatingController extends BasicController{

    private final EmployeeService employeeService;

    @GetMapping("/rating")
    public String rating(Model model, Authentication authentication) {
        model.addAttribute("ratings", employeeService.getRatingEmployee());
        model.addAttribute("user", getEmployee(authentication));
        return "rating";
    }
}

