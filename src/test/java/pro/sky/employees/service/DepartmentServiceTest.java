package pro.sky.employees.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.employees.Employee;
import pro.sky.employees.Exceptions.EmployeeNotFoundException;
import pro.sky.employees.Service.DepartmentService;
import pro.sky.employees.Service.EmployeeService;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 50_000, 1),
            new Employee("Петр", "Петров", 55_000, 1),
            new Employee("Федор", "Федоров", 60_000, 3),
            new Employee("Александр", "Александров", 90_000, 3),
            new Employee("Андрей", "Андреев", 80_000, 2)
    );

    @BeforeEach
    public void beforeEach() {
        when(employeeService.findAll()).thenReturn(employees);
    }

    @Test
    public void findAllByDepartmentId() {
        assertThat(departmentService.findAllByDepartmentId(1)).containsExactlyInAnyOrder(
                new Employee("Иван", "Иванов", 50_000, 1),
                new Employee("Петр", "Петров", 55_000, 1));
    }

    @Test
    public void calculateSumOfSalariesOfEmployeesFromDepartmentTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(1))
                .isEqualTo(105_000);
    }

    @Test
    public void calculateSumOfSalariesOfEmployeesFromDepartmentNegativeTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(4))
                .isEqualTo(0);
    }

    @Test
    public void employeeWithMaxSalaryTest() {
        assertThat(departmentService.employeeWithMaxSalary(1))
                .isEqualTo("Петр Петров");
    }

    @Test
    public void employeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.employeeWithMaxSalary(4));
    }

    @Test
    public void employeeWithMinSalaryTest() {
        assertThat(departmentService.employeeWithMinSalary(1))
                .isEqualTo("Иван Иванов");
    }

    @Test
    public void employeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.employeeWithMinSalary(5));
    }

    @Test
    public void findAllGroupedByDepartmentIdTest() {
        assertThat(departmentService.findAllGroupedByDepartmentId())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Иван", "Иванов", 50_000, 1), new Employee("Петр", "Петров", 55_000, 1)),
                                2, List.of(new Employee("Андрей", "Андреев", 80_000, 2)),
                                3, List.of(new Employee("Федор", "Федоров", 60_000, 3), new Employee("Александр", "Александров", 90_000, 3))
                        )
                );
    }


}


