package analytics.dto;

import analytics.entity.Client;
import analytics.entity.Employee;

import java.util.ArrayList;
import java.util.List;

public class ClientConvert {

    public ArrayList<Client> convert(List<Employee> employees) {
        ArrayList<Client> userList = new ArrayList<>();
        for (Employee employee : employees) {
            Client user = new Client();
            user.setName(employee.getLogin());
            user.setPassword(employee.getPassword());
            user.setRole(employee.getRole());
            userList.add(user);
        }
        return userList;
    }
}
