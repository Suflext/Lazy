package analytics.controller;

import analytics.service.EmployeeService;
import analytics.service.JobPositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class JobPositionsController extends BasicController {

    private final JobPositionService jobPositionService;
    private final EmployeeService employeeService;

    @GetMapping("/jobPositions")
    public String jobPosition(Model model, Authentication authentication) {
        model.addAttribute("jobPositions", jobPositionService.getAll());
        model.addAttribute("user", getEmployee(authentication));
        return "jobPositions";
    }

    @GetMapping("/positEmployee")
    public String departEmployee(@RequestParam long id, Model model, Authentication authentication){
        model.addAttribute("employees", employeeService.getAllByPositionId(id));
        model.addAttribute("user", getEmployee(authentication));
        return "listEmployee";
    }
}
