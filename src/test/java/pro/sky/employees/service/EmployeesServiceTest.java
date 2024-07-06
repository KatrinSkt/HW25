package pro.sky.employees.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.employees.Employee;
import pro.sky.employees.Exceptions.EmployeeAlreadyAddedException;
import pro.sky.employees.Exceptions.EmployeeNotFoundException;
import pro.sky.employees.Exceptions.EmployeeStorageIsFullException;
import pro.sky.employees.Service.EmployeeService;
import pro.sky.employees.Service.ValidationService;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class EmployeesServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new ValidationService());
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 50_000, 1),
            new Employee("Петр", "Петров", 55_000, 2),
            new Employee("Федор", "Федоров", 60_000, 3)
    );

    @BeforeEach
    public void beforeEach() {
        employees.forEach(employee -> employeeService.add(employee.getFirstName(), employee.getLastName(), employee.getSalary(), employee.getDepartmentId()));
    }

    @AfterEach
    public void afterEach() {
        employeeService.findAll().forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName()));
    }

    @Test
    public void addTest() {
        Employee expected = new Employee("Андрей", "Иванов", 50_000, 3);
        Employee actual = employeeService.add("Андрей", "Иванов", 50_000, 3);
        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Андрей", "Иванов"));
        assertThat(actual).isIn(employeeService.findAll());
    }

    @Test
    public void addNegativeTest() {
        employeeService.add("Андрей", "Иванов", 50_000, 3);
        employeeService.add("Артем", "Иванов", 80_000, 2);
        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Виктор", "Иванов", 95_000, 1));
    }

    @Test
    public void addNegativeTest2() {
        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Иван", "Иванов", 50_000, 1));
    }

    @Test
    public void findTest() {
        Employee expected = new Employee("Иван", "Иванов", 50_000, 1);
        assertThat(employeeService.findAll().contains(expected));
        Employee actual = employeeService.find("Иван", "Иванов");
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Андрей", "Иванов"));
    }

    @Test
    public void removeTest() {
        Employee expected = new Employee("Иван", "Иванов", 50_000, 1);
        assertThat(employeeService.findAll().contains(expected));
        assertThat(employeeService.find("Иван", "Иванов")).isEqualTo(expected);
        Employee actual = employeeService.remove("Иван", "Иванов");
        assertThat(actual).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Иван", "Иванов"));
        assertThat(actual).isNotIn(employeeService.findAll());
    }

    @Test
    public void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Андрей", "Иванов"));
    }

    @Test
    public void findAllTest() {
        assertThat(employeeService.findAll())
                .containsExactlyInAnyOrderElementsOf(employees);

    }


}
