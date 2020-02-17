package analytics.controller;

import analytics.service.WorkLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

@Controller
@RequiredArgsConstructor
public class DiagramController extends BasicController {

    private final WorkLogService workLogService;

    @GetMapping("/diagram")
    public String diagram(Model model, Authentication authentication) {
        Map<LocalDate, Long> map = workLogService.getListStartAndFinishWorkWeekByEmployeeId(getEmployee(authentication).getId());
        TreeMap<LocalDate, Long> reverse = new TreeMap<>(map);

        model.addAttribute("key", reverse.keySet());
        model.addAttribute("work", reverse.values());

        return "diagram";
    }
}
