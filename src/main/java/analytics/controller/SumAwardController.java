package analytics.controller;

import analytics.entity.Employee;
import analytics.service.EmployeeService;
import analytics.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SumAwardController {

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/sumAward")
    public String sumAward(Model model) {
        long sumAward = 0;
        for (Employee employee : employeeService.getAll()) {
            long salary = employee.getJobPosition().getSalary();
            float percent = (float) systemPropertiesService.getKey("bonus") / 100;
            sumAward += (long) (salary * percent);
        }
        model.addAttribute("sumAward", sumAward);
        return "sumAward";
    }
}
