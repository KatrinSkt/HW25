package pro.sky.employees.Controllers;

import org.springframework.web.bind.annotation.*;
import pro.sky.employees.Employee;
import pro.sky.employees.Service.DepartmentService;

import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("{id}/salary/max")
    public Employee employeeWithMaxSalary(@PathVariable("id") int departmentId) {
        return departmentService.employeeWithMaxSalary(departmentId);
    }

    @GetMapping("{id}/salary/min")
    public Employee employeeWithMinSalary(@PathVariable("id") int departmentId) {
        return departmentService.employeeWithMinSalary(departmentId);
    }
    @GetMapping("/{id}/employees")
    public List<Employee> findAllByDepartmentId(@PathVariable("id") int departmentId) {
        return departmentService.findAllByDepartmentId(departmentId);
    }
    @GetMapping("/{id}/salary/sum")
    public int calculateSumOfSalariesOfEmployeesFromDepartment(@PathVariable("id") int departmentId) {
        return departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllGroupedByDepartmentId() {
        return departmentService.findAllGroupedByDepartmentId();
    }
}
