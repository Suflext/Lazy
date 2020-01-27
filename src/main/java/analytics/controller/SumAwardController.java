package analytics.controller;

import analytics.entity.Employee;
import analytics.entity.JobPosition;
import analytics.service.EmployeeService;
import analytics.service.JobPositionService;
import analytics.service.SystemPropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class SumAwardController {

    @Autowired
    private JobPositionService jobPositionService;

    @Autowired
    private SystemPropertiesService systemPropertiesService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping(value = "/sumAward")
    public String award(Model model) {
        ArrayList<Employee> employees = employeeService.findAll();
        long award = 0;
        for (Employee employee : employees) {
            JobPosition jobPosition = jobPositionService.getJobPositionByEmployee(employee);
            long salary = jobPosition.getSalary();
            float percent = (float) systemPropertiesService.getPercent("bonus") / 100;
            award += (long) (salary * percent);
        }
        model.addAttribute("award", award);

        return "sumAward";
    }
}
