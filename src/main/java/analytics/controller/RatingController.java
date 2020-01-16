package analytics.controller;

import analytics.service.EmployeeService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class RatingController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/rating")
    public String rating(Model model) {
        int i = 0;
        ArrayList<Format> formats = new ArrayList<>();
        for (String item :
                employeeService.getRatingEmployee()) {
            Format format = new Format();
            format.setId(++i);
            format.setFirstName(item.split(",")[0]);
            format.setLastName(item.split(",")[1]);
            format.setLogin(item.split(",")[2]);
            format.setTime(getTime(item.split(",")[3]));
            formats.add(format);
        }

        model.addAttribute("ratings", formats);
        return "rating";
    }

    private String getTime(String item) {
        long count = Long.parseLong(item.split(" ")[0]);
        String part = item.split(" ")[1].split(":")[0];
        long hour = Long.parseLong(part);
        return (count*24 + hour + item.substring(item.indexOf(part) + 2, item.indexOf(".")));
    }
}

@Getter
@Setter
@NoArgsConstructor
class Format{
    private long id;
    private String firstName;
    private String lastName;
    private String login;
    private String Time;
}