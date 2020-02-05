package analytics;

import analytics.config.EmployeePrincipal;
import analytics.entity.Employee;
import org.springframework.security.core.Authentication;

public class General {
    protected Employee getEmployee(Authentication authentication) {
        return ((EmployeePrincipal) authentication.getPrincipal()).getEmployee();
    }

    public enum ReportPeriodType {week, month, year}

    public String getStringFormatDuration(long duration) {
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
}
