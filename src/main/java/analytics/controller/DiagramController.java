package analytics.controller;

import analytics.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import static java.time.DayOfWeek.MONDAY;
import static java.time.LocalDate.now;

@Controller
@RequiredArgsConstructor
public class DiagramController extends BasicController {

    private final WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
        LocalDate start = now().with(MONDAY);
        LocalDate end = now();
        Map<LocalDate, Long> map = workLogService.getListBetweenTwoDateByEmployee(getEmployee(authentication), start, end);
        TreeMap<LocalDate, Long> reverse = new TreeMap<>(map);

        List<String> list = new ArrayList<>();
        reverse.keySet().forEach(localDate -> {
            list.add(localDate.getDayOfWeek().toString());
        });

        System.out.println(list);

        model.addAttribute("key", list);
        model.addAttribute("work", reverse.values());
        model.addAttribute("user", getEmployee(authentication));
        return "diagram";
    }
}
