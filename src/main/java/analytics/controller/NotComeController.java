package analytics.controller;

import analytics.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class NotComeController extends BasicController {

    private final EmployeeService employeeService;

    @GetMapping("/notComeToday")
    public String notComeToday(Model model, Authentication authentication) {
        if (getEmployee(authentication).getRole().equals("ADMIN")) {
            model.addAttribute("notComeToday", employeeService.getNotComeToday());
            model.addAttribute("user", getEmployee(authentication));
            return "notComeToday";
        } else {
            return "redirect:/index";
        }
    }
}
