package pro.sky.employees;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    public final List<Employee> employees = new ArrayList<>(List.of());
    private final int maxEmployeesCounts = 5;

    public Employee addNewEmployee(String firstName, String lastName) throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {
        if (this.employees.size() >= maxEmployeesCounts) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        }
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName) && employee.getLastName().equals(lastName)) {
                throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
            }
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        System.out.println("Добавлен сотрудник " + employee.toString());
        return employee;
    }

    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        employees.remove(employee);
        return employee;
    }

    public Employee findEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        System.out.println("Поиск сотрудника " + firstName + lastName);
        for (Employee employee : employees) {
            if (employee.getFirstName().contains(firstName) && employee.getLastName().contains(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("Сотрудник не найден");
    }

    public List<Employee> getEmployees() {
        return employees;
    }
}
