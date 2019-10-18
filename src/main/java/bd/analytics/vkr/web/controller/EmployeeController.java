package bd.analytics.vkr.web.controller;

import bd.analytics.vkr.web.entity.Employee;
import bd.analytics.vkr.web.service.DepartmentService;
import bd.analytics.vkr.web.service.EmployeeService;
import bd.analytics.vkr.web.service.PositionService;
import bd.analytics.vkr.web.service.WorkLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @Autowired
    private WorkLogService workLogService;

    @GetMapping(value = {"/", "/index"})
    public String index(){
       return "index";
    }

    @PostMapping(value = {"/log"})
    public String logout(@ModelAttribute Employee employee){
        employeeService.addEndDate(new Date(), employee.getLogin());
        return "redirect:/logout";
    }

    @GetMapping("/user")
    public String user(Model model) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();
        String login = authentication.getName();
        String firstName = employeeService.findNameByLogin(login);
        model.addAttribute("name", firstName);
        model.addAttribute("user", login);
        model.addAttribute("role", authentication.getAuthorities());
        model.addAttribute("employee", employeeService.findByLogin(login));
        employeeService.addStartDate(new Date(), login);
        return "user";
    }

    @GetMapping("/all")
    public String employee(Model model) {
        //employeeService.add();
        model.addAttribute("worklogs", workLogService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("positions", positionService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "all";
    }
}