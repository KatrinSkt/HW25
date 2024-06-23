package pro.sky.employees.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employees.Employee;
import pro.sky.employees.Service.EmployeeService;
import pro.sky.employees.Service.ValidationService;

import java.util.Collection;
import java.util.List;

public class EmployeesServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new ValidationService());
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 50_000, 1),
            new Employee("Петр", "Петров", 55_000, 2),
            new Employee("Федор", "Федоров", 60_000, 3)
    );

    @BeforeEach
    public void beforeEach() {
        employees.forEach(employee -> employeeService.add(
                employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartmentId()));
    }

    @AfterEach
    public void afterEach() {
        employeeService.findAll()
                .forEach(Employee -> employeeService.remove(Employee.getFirstName(), Employee.getLastName()));
    }

    @Test
    public void addTest(String firstName, String lastName, int salary, int department) {
        Employee expected = new Employee("Андрей", "Иванов", 50_000, 3);
        Employee actual = employeeService.add("Андрей", "Иванов", 50_000, 3);
        Assertions.assertThat(actual).isEqualTo(expected);
    }


    public Employee removeTest(String firstName, String lastName) {
        return null;
    }


    public Employee findTest(String firstName, String lastName) {
        return null;
    }

    public Collection<Employee> findAllTest() {
        return null;
    }
}
