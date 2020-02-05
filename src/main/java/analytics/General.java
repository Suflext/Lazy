package analytics;

import analytics.config.EmployeePrincipal;
import analytics.entity.Employee;
import analytics.entity.WorkLog;
import org.springframework.security.core.Authentication;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;
import static java.time.LocalTime.now;
import static java.time.LocalTime.parse;

public class General {

    protected Employee getEmployee(Authentication authentication) {
        return ((EmployeePrincipal) authentication.getPrincipal()).getEmployee();
    }

    public enum ReportPeriodType {week, month, year}

    protected String getStringFormatDuration(long duration) {
        long minutes = 0, hours = 0;
        if (duration >= 60) {
            minutes = duration / 60;
            duration -= minutes * 60;
        }
        if (minutes >= 60) {
            hours = minutes / 60;
            minutes -= hours * 60;
        }
        return hours + " hours " + minutes + " minutes " + duration + " seconds";
    }

    protected List<Double> getListStartAndFinishTimeWork(List<WorkLog> workLogs) {
        ArrayList<Double> startTimeWork = new ArrayList<>(), finishTimeWork = new ArrayList<>();

        workLogs.subList(0, workLogs.size() / 2).forEach(workLog -> {
            if (workLog.getStartTime() != null) {
                add(startTimeWork, workLog.getStartTime());
            } else startTimeWork.add(0d);
        });
        workLogs.subList(workLogs.size() / 2, workLogs.size()).forEach(workLog -> {
            if (workLog.getStartTime() != null) {
                LocalTime end = workLog.getEndTime();
                add(finishTimeWork, end == null ? now() : end);
            } else finishTimeWork.add(0d);
        });

        ArrayList<Double> listStartAndFinishTimeWork = new ArrayList<>(startTimeWork);
        listStartAndFinishTimeWork.addAll(finishTimeWork);
        return listStartAndFinishTimeWork;
    }

    private void add(ArrayList<Double> list, LocalTime time) {
        long seconds = Duration.between(parse("00:00:00"), time).getSeconds();
        long minutes = seconds / 60;
        long hour = minutes / 60;
        minutes -= hour * 60;
        list.add(parseDouble(hour + "." + minutes));
    }

    protected String getTime(String item) {
        long countDay = parseLong(item.split(" ")[0]);
        String part = item.split(" ")[1].split(":")[0];
        long hour = parseLong(part);
        return (countDay * 24 + hour + item.substring(item.indexOf(part) + 2, item.indexOf(".")));
    }
}
