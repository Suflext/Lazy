package bd.analytics.vkr.web.controller;

import bd.analytics.vkr.web.service.DepartmentService;
import bd.analytics.vkr.web.service.EmployeeService;
import bd.analytics.vkr.web.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PositionService positionService;

    @GetMapping(value = {"/", "/index"})
    public String index(){
       return "index";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }




    @GetMapping("/all")
    public String employee(Model model) {
        //employeeService.add();
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("positions", positionService.findAll());
        model.addAttribute("departments", departmentService.findAll());
        return "all";
    }

}