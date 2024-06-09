package pro.sky.employees.Controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.employees.Service.DepartmentService;
import pro.sky.employees.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping("/max-salary")
    public Employee employeeWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.employeeWithMaxSalary(departmentId);
    }

    @GetMapping("/min-salary")
    public Employee employeeWithMinSalary(@RequestParam int departmentId) {
        return departmentService.employeeWithMinSalary(departmentId);
    }
    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> findAllByDepartmentId(@RequestParam int departmentId) {
        return departmentService.findAllByDepartmentId(departmentId);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> findAllByDepartmentId() {
        return departmentService.findAllByDepartmentId();
    }
}
