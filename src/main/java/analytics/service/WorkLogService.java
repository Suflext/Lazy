package analytics.service;

import analytics.entity.Employee;
import analytics.entity.WorkLog;
import analytics.repository.EmployeeRepository;
import analytics.repository.WorkLogRepository;
import analytics.service.model.viewAward;
import analytics.service.model.viewSumAward;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.time.Duration.between;
import static java.time.LocalDate.now;
import static java.util.stream.Collectors.groupingBy;

@Service
@RequiredArgsConstructor
public class WorkLogService {

    private final WorkLogRepository workLogRepo;
    private final WorkLogRepo workLogRe;
    private final EmployeeRepository employeeRepo;

    public List<WorkLog> getAll() {
        return workLogRepo.findAll();
    }

    public void addStartDate(Employee employee) {
        WorkLog workLog = new WorkLog();
        workLog.setStartTime(LocalTime.now());
        workLog.setDay(now());
        workLog.setEmployee(employee);
        workLogRepo.save(workLog);
    }

    public void addEndDate(Employee employee) {
        WorkLog workLog = workLogRepo.findByEmployeeId(employee.getId());
        workLog.setEndTime(LocalTime.now());
        workLog.setDuration(between(workLog.getStartTime(), LocalTime.now()).getSeconds());
        workLogRepo.save(workLog);
    }

    public Long getTimeWorkByDayAndEmployeeId(LocalDate day, Employee employee) {
        return workLogRe.getTimeWorkByDayAndEmployeeId(day, employee).getSeconds();
    }

    public Boolean isLateness(Employee employee, LocalDate date) {
        if (employee.getJobPosition().getStartTime() != null) {
            List<WorkLog> workLogs = workLogRepo.findAllBetweenTwoDateByEmployeeId(employee.getId(), now().withDayOfMonth(1), now());
            for (WorkLog workLog : workLogs) {
                if (workLog.getDay().equals(date) && workLog.getStartTime().isAfter(employee.getJobPosition().getStartTime())) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<WorkLog> getEmployeeWhoWork() {
        return workLogRepo.findEmployeeWhoWork();
    }

    public Map<LocalDate, Long> getListBetweenTwoDateByEmployee(Employee employee, LocalDate start, LocalDate end) {
        List<WorkLog> workLogs = workLogRepo.findAllBetweenTwoDateByEmployeeId(employee.getId(), start, end);

        Map<LocalDate, Long> longMap = new HashMap<>();
        Map<LocalDate, List<WorkLog>> listMap = workLogs.stream().collect(groupingBy(WorkLog::getDay));
        listMap.forEach((key, value) -> longMap.put(
                key,
                value.stream()
                        .mapToLong(workLog ->
                                between(workLog.getStartTime(),
                                        workLog.getEndTime() == null
                                                ? LocalTime.now()
                                                : workLog.getEndTime())
                                        .getSeconds()).sum()));
        return longMap;
    }

    public List<viewAward> getListAwards(Employee employee, float bonus, float penalty) {
        long countDays = Period.between(now().withDayOfMonth(1), now()).getDays() + 1;
        Map<LocalDate, Long> timeWorked = getListBetweenTwoDateByEmployee(employee, now().withDayOfMonth(1), now());
        List<viewAward> listAwards = new ArrayList<>();
        long salary = employee.getJobPosition().getSalary();
        long award = salary;

        for (int i = 1; i <= countDays; i++) {
            LocalDate date = now().withDayOfMonth(i);
            String typeDay = "";
            boolean absence = true;
            boolean lateness = false;
            long overwork = calculateOverwork(timeWorked, date);

            if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                typeDay = "Выходной";
            } else {
                typeDay = "Рабочий день";
            }

            if (timeWorked.get(date) != null) absence = false;
            else {
                award -= (long) (salary * penalty * 2);
            }

            if (isLateness(employee, date)) {
                lateness = true;
                award -= (long) (salary * penalty);
            }

            award += salary * bonus * overwork;

            listAwards.add(viewAward.builder()
                    .id(i)
                    .date(date)
                    .typeDay(typeDay)
                    .absence(absence ? "Да" : "Нет")
                    .lateness(lateness ? "Да" : "Нет")
                    .overwork(overwork)
                    .award(award).build());
        }
        return listAwards;
    }

    public List<viewSumAward> getSumAward(float bonus, float penalty) {
        long n = 0;
        long countDays = Period.between(now().withDayOfMonth(1), now()).getDays() + 1;
        List<viewSumAward> awardsList = new ArrayList<>();

        for (Employee employee : employeeRepo.findAll()) {
            Map<LocalDate, Long> timeWorked = getListBetweenTwoDateByEmployee(employee, now().withDayOfMonth(1), now());
            long salary = employee.getJobPosition().getSalary();
            long award = salary;
            for (int i = 1; i <= countDays; i++) {
                LocalDate date = now().withDayOfMonth(i);
                long overwork = calculateOverwork(timeWorked, date);

                if (timeWorked.get(date) == null) {
                    award -= (long) (salary * penalty * 2);
                }

                if (isLateness(employee, date)) {
                    award -= (long) (salary * penalty);
                }

                award += salary * bonus * overwork;
            }

            awardsList.add(viewSumAward.builder()
                    .id(++n)
                    .employeeId(employee.getId())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .jobPosition(employee.getJobPosition().getName())
                    .department(employee.getDepartment().getName())
                    .award(award).build());
        }
        return awardsList;
    }

    private Long calculateOverwork(Map<LocalDate, Long> timeWorked, LocalDate date) {
        long overwork = 0;
        if (timeWorked.get(date) != null) {
            long workedHours = timeWorked.get(date) / 3600;
            if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                overwork = workedHours;
            } else if (workedHours > 8) {
                overwork = workedHours - 8;
            }
        }
        return overwork;
    }
}
