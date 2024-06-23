package pro.sky.employees.Service;

import org.springframework.stereotype.Service;
import pro.sky.employees.Employee;
import pro.sky.employees.Exceptions.EmployeeNotFoundException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentService{
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    public Employee employeeWithMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .max(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }


    public Employee employeeWithMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .min(comparingInt(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }


    public List<Employee> findAllByDepartmentId(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .collect(Collectors.toList());
    }


    public int calculateSumOfSalariesOfEmployeesFromDepartment(int departmentId) {
        return employeeService.findAll().stream()
                .filter(e -> e.getDepartmentId() == departmentId)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }


    public Map<Integer, List<Employee>> findAllGroupedByDepartmentId() {
        return employeeService.findAll().stream()
                .collect(groupingBy(Employee::getDepartmentId));
    }


}
